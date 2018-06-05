package com.hsbc.atm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hsbc.atm.constants.StringConstants;
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
		return calculateBalance();
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
		Set<Integer> keySet = currencyToNotesMap.keySet();
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			amount = populateAmntForDispensing(amount, key);
		}
		if(amount>0){
			LOGGER.info("!!! Amount can not dispensed due to lack of notes!!!");
			return Status.INVALID;
        }else{
            for (Entry<Integer, Integer> entry : currentTransactionHoldings.entrySet()) {
                LOGGER.info(entry.getKey() + "$ bills: " + entry.getValue());
                // Now reduce the same number from currency store
                currencyToNotesMap.put(entry.getKey(),currencyToNotesMap.get(entry.getKey()) - entry.getValue());
                // TODO: prepare response with notes details
            }
        }
		currentTransactionHoldings.clear();
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
            currencyToNotesMap.put(factor, 0);
        }else{
            // When ATM have sufficient bills
            currentTransactionHoldings.put(factor, bills);
            amount = amount % factor;
            //currencyToNotesMap.put(factor, availableBills - bills);
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
}
