package com.hsbc.atm.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hsbc.atm.model.Response;
import com.hsbc.enums.Status;

/**
 * Test class for ResponseFactory
 * @author Ayush Verma
 *
 */
public class ResponseFactoryTest {
	private static final String SUCCESS_RESPONSE = "Amount successfully witdrawan.";
	private static final String FAILURE_RESPONSE = "Amount witdrawal failed.";
	private static final String INSUFFICIENT_RESPONSE = "Insufficient funds.";
	private static final String INVALID_RESPONSE = "Invalid amount to withdraw.";

	@Test
	public void testSuccessResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.SUCCESS);
		Assert.assertEquals(successResponse.getStatus(), Status.SUCCESS);
		Assert.assertEquals(successResponse.getMessage(), SUCCESS_RESPONSE);
	}

	@Test
	public void testFailureResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.FAILED);
		Assert.assertEquals(successResponse.getStatus(), Status.FAILED);
		Assert.assertEquals(successResponse.getMessage(), FAILURE_RESPONSE);
	}

	@Test
	public void testInsufficientResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.INSUFFICIENT);
		Assert.assertEquals(successResponse.getStatus(), Status.INSUFFICIENT);
		Assert.assertEquals(successResponse.getMessage(), INSUFFICIENT_RESPONSE);
	}

	@Test
	public void testInvalidResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.INVALID);
		Assert.assertEquals(successResponse.getStatus(), Status.INVALID);
		Assert.assertEquals(successResponse.getMessage(), INVALID_RESPONSE);
	}
}
