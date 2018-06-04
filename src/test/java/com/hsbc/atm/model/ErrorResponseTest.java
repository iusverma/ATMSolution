package com.hsbc.atm.model;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hsbc.enums.Status;

/**
 * Error response test class
 * @author Ayush Verma
 */
public class ErrorResponseTest {
	private static final String SUCCESS_MESSAGE = "Withdrawal successful";
	private static final String FAILURE_MESSAGE = "Withdrawal rejected";
	
	private ErrorResponse getResponse(Status status, String message) {
		ErrorResponse response = new ErrorResponse();
		response = new ErrorResponse();
		response.setBalance(100);
		response.setStatus(status);
		response.setAmountWithdrawn(50);
		response.setMessage(message);
		return response;
	}

	@Test
	public void testBasicSuccessResponse() {
		ErrorResponse response = getResponse(Status.SUCCESS,SUCCESS_MESSAGE);
		Assert.assertEquals(response.getStatus(), Status.SUCCESS);
		Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		Assert.assertEquals(response.getBalance(), 100);
		Assert.assertEquals(response.getAmountWithdrawn(), 50);
	}

	@Test
	public void testBasicFailureResponse() {
		ErrorResponse response = getResponse(Status.FAILED,FAILURE_MESSAGE);
		response.setAmountWithdrawn(0);
		Assert.assertEquals(response.getStatus(), Status.FAILED);
		Assert.assertEquals(response.getMessage(), FAILURE_MESSAGE);
		Assert.assertEquals(response.getBalance(), 100);
		Assert.assertEquals(response.getAmountWithdrawn(), 0);
	}
}
