package com.hsbc.atm.model;

/**
 * Request
 * @author Ayush Verma
 * Request for withdrawl
 */
public class Request {

	/** Withdrawal amount requested */
	private int amount;

	/**
	 * Getter for requested amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Setter for requested amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
