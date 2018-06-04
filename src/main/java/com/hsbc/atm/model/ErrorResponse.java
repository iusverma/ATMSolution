package com.hsbc.atm.model;

/**
 * WithdrawalResponse
 * @author Ayush Verma
 * Response message for a successfull response
 */
public class ErrorResponse extends Response {
	/** Amount withdrawn */
	private int amountWithdrawn;

	/** Remaining balance in ATM */
	private int balance;

	/**
	 * Getter for amount withdrawn
	 */
	public int getAmountWithdrawn() {
		return amountWithdrawn;
	}

	/**
	 * Getter for ATM balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Setter for amount withdrawn
	 */
	public void setAmountWithdrawn(int amountWithdrawn) {
		this.amountWithdrawn = amountWithdrawn;
	}

	/**
	 * Setter for ATM balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
