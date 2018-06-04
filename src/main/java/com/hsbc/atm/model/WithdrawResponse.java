package com.hsbc.atm.model;

/**
 * WithdrawalResponse
 * @author Ayush Verma
 * Response message for a successful response
 */
public class WithdrawResponse extends Response {
	/** Amount requested */
	private int amountRequested;

	/** Remaining balance in ATM */
	private int balance;

	/**
	 * Getter for amount requested
	 */
	public int getAmountRequested() {
		return amountRequested;
	}

	/**
	 * Getter for ATM balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Setter for amount requested
	 */
	public void setAmountRequested(int amountRequested) {
		this.amountRequested = amountRequested;
	}

	/**
	 * Setter for ATM balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
