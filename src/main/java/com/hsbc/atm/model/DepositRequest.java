package com.hsbc.atm.model;

import java.util.Map;

/**
 * DepositRequest
 * @author Ayush Verma
 * Class defines the deposit request
 */
public class DepositRequest extends Request {
	/** List and number of notes deposited */
	private Map<Integer, Integer> notesToDeposit;

	/**
	 * Getter for notesToDeposit;
	 */
	public Map<Integer, Integer> getNotesToDeposit() {
		return notesToDeposit;
	}

	/**
	 * Setter for notesToDeposit;
	 */
	public void setNotesToDeposit(Map<Integer, Integer> notesToDeposit) {
		this.notesToDeposit = notesToDeposit;
	}
}
