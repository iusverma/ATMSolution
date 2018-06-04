package com.hsbc.enums;

/**
 * Status
 * @author Ayush Verma
 * Possible status for a withdrawal request
 */
public enum Status {
	/** SUCCESS withdrawal */
	SUCCESS,

	/** FAILED withdrawal */
	FAILED,

	/** INVALID amount */
	INVALID,

	/** INSUFFICIENT amount */
	INSUFFICIENT;
}
