package com.hsbc.atm.model;

import com.hsbc.enums.Status;

/**
 * Response class
 * @author Ayush Verma
 * Default response for the app
 */
public class Response {
	/**
	 * Default response for the request
	 */
	private Status status;

	/**
	 * Message for the transaction
	 */
	private String message;

	/** Amount requested */
	private int amount;

	/** Stores information of denomination and their current balance */
	private BillsDetail billsDetail;

	/**
	 * Getter for status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Setter for default status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Getter for message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter for amount requested
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Setter for amount requested
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BillsDetail getBillsDetail() {
		return billsDetail;
	}

	public void setBillsDetail(BillsDetail billsDetail) {
		this.billsDetail = billsDetail;
	}
}
