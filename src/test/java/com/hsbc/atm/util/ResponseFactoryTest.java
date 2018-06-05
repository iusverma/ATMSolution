package com.hsbc.atm.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hsbc.atm.model.Response;
import com.hsbc.enums.OperationType;
import com.hsbc.enums.Status;

/**
 * Test class for ResponseFactory
 * @author Ayush Verma
 *
 */
public class ResponseFactoryTest {

	@Test
	public void testSuccessResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.SUCCESS,OperationType.DEPOSIT);
		Assert.assertEquals(successResponse.getStatus(), Status.SUCCESS);
		Assert.assertEquals(successResponse.getMessage(), ResponseMessage.getResponseMessage(ResponseMessage.SUCCESS_DEPOSIT));
	}

	@Test
	public void testFailureResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.FAILED, OperationType.WITHDRAWAL);
		Assert.assertEquals(successResponse.getStatus(), Status.FAILED);
		Assert.assertEquals(successResponse.getMessage(), ResponseMessage.getResponseMessage(ResponseMessage.FAILED_WITHDRAWAL));
	}

	/*
	@Test
	public void testInsufficientResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.INSUFFICIENT);
		Assert.assertEquals(successResponse.getStatus(), Status.INSUFFICIENT);
		Assert.assertEquals(successResponse.getMessage(), INSUFFICIENT_RESPONSE);
	}
	*/
	@Test
	public void testInvalidResponseFromStatus() {
		Response successResponse = ResponseFactory.getResponseFromStatus(Status.INVALID, OperationType.DEPOSIT);
		Assert.assertEquals(successResponse.getStatus(), Status.INVALID);
		Assert.assertEquals(successResponse.getMessage(), ResponseMessage.getResponseMessage(ResponseMessage.INVALID_DEPOSIT));
	}
}
