package com.hsbc.atm.util;

import com.hsbc.atm.model.Response;
import com.hsbc.enums.OperationType;
import com.hsbc.enums.Status;

/**
 * ResponseFactory class
 * @author Ayush Verma
 * This class will be responsible for creating
 * new object for different response types.
 */
public class ResponseFactory {

	/**
	 * Currently only Response class is supported.
	 * In future when detailed status are present,
	 * it will create different objects
	 */
	public static Response getResponseFromStatus(Status status, OperationType operation) {
		Response response = new Response();
		response.setStatus(status);
		String key = status + "_" + operation;
		response.setMessage(ResponseMessage.getResponseMessage(key));
		return response;
	}
}
