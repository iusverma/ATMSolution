package com.hsbc.atm.util;

import com.hsbc.atm.model.Response;
import com.hsbc.enums.Status;

/**
 * ResponseFactory class
 * @author Ayush Verma
 * This class will be responsible for creating
 * new object for different response types.
 */
public class ResponseFactory {
	/** Success Response Message */
	private static final String SUCCESS_RESPONSE = "Amount successfully witdrawan.";

	/** Failure Response Message */
	private static final String FAILURE_RESPONSE = "Amount witdrawal failed.";

	/** Insufficient Funds Response Message */
	private static final String INSUFFICIENT_RESPONSE = "Insufficient funds.";

	/** Invalid Amount Response Message */
	private static final String INVALID_RESPONSE = "Invalid amount to withdraw.";

	/**
	 * Currently only Response class is supported.
	 * In future when detailed status are present,
	 * it will create different objects
	 */
	public static Response getResponseFromStatus(Status status) {
		Response response = new Response();
		response.setStatus(status);
		switch(status) {
		case SUCCESS:
			response.setMessage(SUCCESS_RESPONSE);
			break;
		case FAILED:
			response.setMessage(FAILURE_RESPONSE);
			break;
		case INSUFFICIENT:
			response.setMessage(INSUFFICIENT_RESPONSE);
			break;
		case INVALID:
			response.setMessage(INVALID_RESPONSE);
			break;
		}
		return response;
	}
}
