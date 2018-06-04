package com.hsbc.atm;

import java.util.HashMap;
import java.util.Map;

import com.hsbc.enums.Status;

/**
 * ATMMachine
 * @author Ayush Verma
 * ATM machine class, responsible for keeping track of balances and notes.
 */
public class ATMMachine {
	/** Remaining balance in ATM */
	private int balance;

	/** Map of each type of notes in ATM */
	private Map<String, Integer> currencyToNotesMap = new HashMap<>();

	/**
	 * Getter for balance
	 */
	public int getBalance() {
		return balance;
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
}
