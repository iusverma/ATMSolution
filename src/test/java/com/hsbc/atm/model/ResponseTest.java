package com.hsbc.atm.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hsbc.enums.Status;

public class ResponseTest {
	private static final String SUCCESS_MESSAGE = "Withdrawal successful";
	private static final String FAILURE_MESSAGE = "Withdrawal rejected";
	
	private Response getResponse(Status status, String message) {
		Response response = new Response();
		response = new Response();
		response.setBalance(100);
		response.setStatus(status);
		response.setAmountRequested(50);
		response.setMessage(message);
		return response;
	}

	@Test
	public void testBasicSuccessResponse() {
		Response response = getResponse(Status.SUCCESS,SUCCESS_MESSAGE);
		Assert.assertEquals(response.getStatus(), Status.SUCCESS);
		Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		Assert.assertEquals(response.getBalance(), 100);
		Assert.assertEquals(response.getAmountRequested(), 50);
	}

	@Test
	public void testBasicFailureResponse() {
		Response response = getResponse(Status.FAILED,FAILURE_MESSAGE);
		response.setAmountRequested(0);
		Assert.assertEquals(response.getStatus(), Status.FAILED);
		Assert.assertEquals(response.getMessage(), FAILURE_MESSAGE);
		Assert.assertEquals(response.getBalance(), 100);
		Assert.assertEquals(response.getAmountRequested(), 0);
	}
}
