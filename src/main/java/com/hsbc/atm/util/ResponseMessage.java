package com.hsbc.atm.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ResponseMessage class
 * @author Ayush Verma
 * This class contains various message for all scenarios
 */
public class ResponseMessage {
	/** Success Response Message */
	private static final String SUCCESS_WITHDRAWAL_RESPONSE = "Amount withdrawan successfully.";
	private static final String SUCCESS_DEPOSIT_RESPONSE = "Amount deposited successfully.";
	private static final String SUCCESS_ENQUIRY_RESPONSE = "Balance enquiry executed successfully";
	public static final String SUCCESS_WITHDRAWAL = "SUCCESS_WITHDRAWAL";
	public static final String SUCCESS_DEPOSIT = "SUCCESS_DEPOSIT";
	public static final String SUCCESS_ENQUIRY = "SUCCESS_ENQUIRY";
	
	/** Failure Response Messages */
	private static final String FAILED_WITHDRAWAL_RESPONSE = "Amount withdrawal failed.";
	public static final String FAILED_WITHDRAWAL = "FAILED_WITHDRAWAL";
	
	/** Invalid Response Messages */
	private static final String INVALID_DEPOSIT_RESPONSE = "Invalid bills for deposit, please check the amount & bill before deposit.";
	public static final String INVALID_DEPOSIT = "INVALID_DEPOSIT";

	/** Map for string messages */
	private static Map<String, String> responseMessages = new HashMap<>();

	/**
	 * Initializer for response message map
	 */
	private static void init(){
		responseMessages.put(SUCCESS_WITHDRAWAL, SUCCESS_WITHDRAWAL_RESPONSE);
		responseMessages.put(SUCCESS_DEPOSIT, SUCCESS_DEPOSIT_RESPONSE);
		responseMessages.put(SUCCESS_ENQUIRY, SUCCESS_ENQUIRY_RESPONSE);
		responseMessages.put(FAILED_WITHDRAWAL, FAILED_WITHDRAWAL_RESPONSE);
		responseMessages.put(INVALID_DEPOSIT, INVALID_DEPOSIT_RESPONSE);
	}

	/**
	 * Get response message based on key
	 */
	public static String getResponseMessage(String key) {
		init();
		return responseMessages.get(key);
	}
}
