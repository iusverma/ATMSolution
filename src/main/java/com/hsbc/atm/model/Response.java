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
	
}
