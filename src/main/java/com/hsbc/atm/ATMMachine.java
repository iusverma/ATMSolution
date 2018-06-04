package com.hsbc.atm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
	private Map<String, Integer> currencyToNotesMap = new HashMap<>();

	/** private constructor, object cannot be created outside */
	private ATMMachine() {
		initializeMap();
	}

	/**
	 * Initialize ATM at the start of application
	 */
	private void initializeMap() {
		LOGGER.info("Initializing ATM balance.");
		currencyToNotesMap.put(StringConstants.TEN_DOLLAR_NOTE,10);
		currencyToNotesMap.put(StringConstants.TWENTRY_DOLLAR_NOTE,10);
		currencyToNotesMap.put(StringConstants.FIFTY_DOLLAR_NOTE,10);
		currencyToNotesMap.put(StringConstants.HUNDRED_DOLLAR_NOTE,10);
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
	public Map<String, Integer> getCurrencyToNotesMap() {
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
	public void setCurrencyToNotesMap(Map<String, Integer> currencyToNotesMap) {
		this.currencyToNotesMap = currencyToNotesMap;
	}

	/**
	 * Reduces the amount in currencyToNotesMap 
	 * @return
	 */
	public Status withdrawAmount(int amount) {
		return Status.SUCCESS;
	}

	/**
	 * Reverts the withdrawal or restores recurrencyToNotesMap
	 * to beginning of the transaction
	 * @return
	 */
	public Status revertTransaction() {
		return Status.SUCCESS;
	}

	/**
	 * Primarily responsible for evaluating
	 * availability of funds.
	 * @return
	 */
	public boolean isAmountAvailable(int amount) {
		return true;
	}

	/**
	 * Calculates the balance based on number
	 * of notes remaining in map
	 * @return
	 */
	private int calculateBalance() {
		int balance = 0;
		Set<String> keySet = currencyToNotesMap.keySet();
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			int value = currencyToNotesMap.get(key);
			if(key.equals(StringConstants.TEN_DOLLAR_NOTE)) {
				balance += value*10;
			}else if(key.equals(StringConstants.TWENTRY_DOLLAR_NOTE)) {
				balance += value*20;
			}else if(key.equals(StringConstants.FIFTY_DOLLAR_NOTE)) {
				balance += value*50;
			}else if(key.equals(StringConstants.HUNDRED_DOLLAR_NOTE)) {
				balance += value*100;
			}
		}
		return balance;
	}
}
