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
		Set<Integer> keySet = currencyToNotesMap.keySet();
		Iterator<Integer> iter = keySet.iterator();
		for(int i=0;i<denominations.length;i++) {
			Integer key = denominations[i];
			if(currencyToNotesMap.containsKey(key)) {
				amount = populateAmntForDispensing(amount, key);
			}
		}
//		while(iter.hasNext() && amount > 0) {
//			Integer key = iter.next();
//			amount = populateAmntForDispensing(amount, key);
//		}
		if(amount>0){
			// check if other combination is present
			LOGGER.info("!!! Amount can not dispensed due to lack of notes!!!");
			return Status.INVALID;
        }else{
            for (Entry<Integer, Integer> entry : currentTransactionHoldings.entrySet()) {
                LOGGER.info(entry.getKey() + "$ bills: " + entry.getValue());
                // Now reduce the same number from currency store
                currencyToNotesMap.put(entry.getKey(),currencyToNotesMap.get(entry.getKey()) - entry.getValue());
            }
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

	//private void check
}
