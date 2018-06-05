package com.hsbc.atm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hsbc.atm.model.DepositRequest;
import com.hsbc.enums.Status;

/**
 * ATMMachine
 * @author Ayush Verma
 * ATM machine class, responsible for keeping track of balances and notes.
 */
public class ATMMachine {
	private static final Logger LOGGER = Logger.getLogger(ATMMachine.class);

	/** static instance for ATMMachine object
	 */
	private static ATMMachine atmMachine = new ATMMachine();

	/** Remaining balance in ATM */
	private int balance;

	/** Map of each type of notes in ATM */
	private Map<Integer, Integer> currencyToNotesMap = new HashMap<>();

	/** Stores information of number of notes to dispense */
	private Map<Integer, Integer> currentTransactionHoldings = new HashMap<>();

	private Integer [] denominations = {100,50,20,10,5};
	/** private constructor, object cannot be created outside */
	private ATMMachine() {
		initializeMap();
	}

	/**
	 * Initialize ATM at the start of application
	 */
	private void initializeMap() {
		LOGGER.info("Initializing ATM balance.");
		currencyToNotesMap.put(10,10);
		currencyToNotesMap.put(20,10);
		currencyToNotesMap.put(50,10);
		currencyToNotesMap.put(100,10);
		LOGGER.info("ATM Balance ==> "+this.getBalance());
	}

	/**
	 * Static getInstance method */
	public static ATMMachine getInstance() {
		return atmMachine;
	}
	/**
	 * Getter for balance
	 */
	public int getBalance() {
		this.balance = calculateBalance();
		return this.balance;
	}
	/**
	 * Getter for currencyToNotesMap
	 */
	public Map<Integer, Integer> getCurrencyToNotesMap() {
		return currencyToNotesMap;
	}
	/**
	 * Setter for balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
	/**
	 * Setter for currencyToNotesMap
	 */
	public void setCurrencyToNotesMap(Map<Integer, Integer> currencyToNotesMap) {
		this.currencyToNotesMap = currencyToNotesMap;
	}

	/**
	 * Reduces the amount in currencyToNotesMap
	 */
	public Status withdrawAmount(int amount) {
		currentTransactionHoldings.clear();
		int requestedAmount = amount;
		for(int i=0;i<denominations.length;i++) {
			Integer key = denominations[i];
			if(currencyToNotesMap.containsKey(key)) {
				amount = populateAmntForDispensing(amount, key);
			}
		}
		if(amount>0){
			// check if other combination is present
			if(checkAlternatives(requestedAmount)!=Status.SUCCESS) {
				LOGGER.info("!!! Amount can not dispensed due to lack of notes!!!");
				return Status.INVALID;
			}
        }
        for (Entry<Integer, Integer> entry : currentTransactionHoldings.entrySet()) {
            LOGGER.info(entry.getKey() + "$ bills: " + entry.getValue());
            // Now reduce the same number from currency store
            currencyToNotesMap.put(entry.getKey(),currencyToNotesMap.get(entry.getKey()) - entry.getValue());
        }
		return Status.SUCCESS;
	}

	/**
	 * This method evaluates exact number of individual
	 * notes for possible denomination
	 */
	private int populateAmntForDispensing(int amount, int factor){
        int bills = 0;
        bills = amount / factor;
        int availableBills = currencyToNotesMap.get(factor);
        if( bills > availableBills){
            // When number of required bills is greater than available bills
            currentTransactionHoldings.put(factor, availableBills);
            amount = amount - (factor * availableBills);
            //currencyToNotesMap.put(factor, 0);
        }else{
            // When ATM have sufficient bills
            currentTransactionHoldings.put(factor, bills);
            amount = amount % factor;
        }
        return amount;
    }

	/**
	 * Reverts the withdrawal or restores recurrencyToNotesMap
	 * to beginning of the transaction
	 */
	public Status revertTransaction() {
		return Status.SUCCESS;
	}

	/**
	 * Primarily responsible for evaluating
	 * availability of funds.
	 */
	public boolean isAmountAvailable(int amount) {
		if(amount<calculateBalance())
			return true;
		return false;
	}

	/**
	 * Calculates the balance based on number
	 * of notes remaining in map
	 */
	private int calculateBalance() {
		int balance = 0;
		Set<Integer> keySet = currencyToNotesMap.keySet();
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			int value = currencyToNotesMap.get(key);
			balance += value*key;
		}
		return balance;
	}

	/**
	 * Deposit currency in ATM

	 */
	public Status depositAmount(DepositRequest request) {
		if(request == null)
			return Status.INVALID;

		Set<Integer> keySet = request.getNotesToDeposit().keySet();
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			if(request.getNotesToDeposit().get(key)>0) {
				currencyToNotesMap.put(key,currencyToNotesMap.get(key)+request.getNotesToDeposit().get(key));
			}
		}
		return Status.SUCCESS;
	}

	/**
	 * Getter for currentTransactionHoldings
	 */
	public Map<Integer, Integer> getCurrentTransactionHoldings() {
		return currentTransactionHoldings;
	}

	/**
	 * Setter for currentTransactionHoldings
	 */
	public void setCurrentTransactionHoldings(Map<Integer, Integer> currentTransactionHoldings) {
		this.currentTransactionHoldings = currentTransactionHoldings;
	}

	/**
	 * In here, I am checking the possibility of any possible combination
	 * for completing the amount, approach is
	 * 1. find the maximum denomination from the stack evaluated
	 * 2. remove one note, reset the remaining amount to be fulfilled
	 * 3. from the denomination to the end, see if any of the lower
	 * denomination can complete the amount
	 */
	private Status checkAlternatives(int requestedAmount) {
		/** Iterating for all denominations */
		for(int i=0;i<denominations.length;i++) {
			/** consider only if a denomination is already chosen for withdraw */
			if(currentTransactionHoldings.containsKey(denominations[i]) &&
					currentTransactionHoldings.get(denominations[i]) >0) {
				/** reset the amount made up using current denomination by remove one denomination */
				int amountAfterOneDenominationIsRemoved =
						(currentTransactionHoldings.get(denominations[i])-1)*denominations[i];
				/** since denomination is reduced, target amount is also reset */
				int targetAmount = requestedAmount - amountAfterOneDenominationIsRemoved;

				/** iterating on all remaining denominations */
				for(int j=i+1;j<denominations.length;j++) {
					/** Check is remaining denomination can make the remaining amount */
					if(targetAmount%denominations[j] == 0) {
						int denominationsAvailable = currencyToNotesMap.get(denominations[j]);
						int notesRequired = targetAmount/denominations[j];

						/** Check if sufficient remaining denomination are present */
						if(denominationsAvailable>=notesRequired) {
							currentTransactionHoldings.put(denominations[i],
									currentTransactionHoldings.get(denominations[i])-1);
							currentTransactionHoldings.put(denominations[j],notesRequired);
							return Status.SUCCESS;
						}
					}
				}
			}
		}
		return Status.INSUFFICIENT;
	}
}
