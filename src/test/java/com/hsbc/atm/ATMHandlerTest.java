package com.hsbc.atm;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hsbc.atm.model.Response;
import com.hsbc.enums.Status;

/**
 * ATMHandlerTest class
 * @author Ayush Verma
 * Test class for ATMHandler
 */
public class ATMHandlerTest {
	private static final String SUCCESS_RESPONSE = "Amount successfully witdrawan.";

	@Test
	public void testAmountWithdrawal() {
		ATMHandler atmHandler = new ATMHandler();
		Response response = atmHandler.withdrawAmount(20);
		Assert.assertEquals(response.getStatus(), Status.SUCCESS);
		Assert.assertEquals(response.getMessage(),SUCCESS_RESPONSE);
		Assert.assertEquals(response.getAmountRequested(), 20);
		Assert.assertEquals(response.getBalance(), 1780);
	}

	@Test
	public void testInsufficientFunds() {
		
	}
}
