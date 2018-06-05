package com.hsbc.atm;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hsbc.atm.model.DepositRequest;
import com.hsbc.atm.model.Response;
import com.hsbc.atm.util.ResponseMessage;
import com.hsbc.enums.Status;

/**
 * ATMHandlerTest class
 * @author Ayush Verma
 * Test class for ATMHandler
 */
public class ATMHandlerTest {
	private static final Integer TEN_DOLLAR_KEY = new Integer(10);
	private static final Integer TWENTY_DOLLAR_KEY = new Integer(20);
	private static final Integer FIFTY_DOLLAR_KEY = new Integer(50);

	@Test
	public void testAmountWithdrawal() {
		ATMHandler atmHandler = new ATMHandler();
		Response response = atmHandler.withdrawAmount(20);
		Assert.assertEquals(response.getStatus(), Status.SUCCESS);
		Assert.assertEquals(response.getMessage(),ResponseMessage.getResponseMessage(ResponseMessage.SUCCESS_WITHDRAWAL));
		Assert.assertEquals(response.getAmount(), 20);
		Assert.assertEquals(response.getBillsDetail().getTotalAmount(), 20);
		Assert.assertEquals(atmHandler.getBalance().getAmount(),1780);
	}

	@Test
	public void testRemaining() {
		ATMHandler atmHandler = new ATMHandler();
		Response response = atmHandler.getBalance();
		Map<Integer, Integer> remainingNotes = response.getBillsDetail().getBillsDetail();
		Assert.assertTrue((int)remainingNotes.get(new Integer(10))>0);
		Assert.assertTrue((int)remainingNotes.get(new Integer(20))>0);
		Assert.assertTrue((int)remainingNotes.get(new Integer(50))>0);
		Assert.assertTrue((int)remainingNotes.get(new Integer(100))>0);
	}

	@Test
	public void testUpdateBalance() {
		ATMHandler atmHandler = new ATMHandler();
		Response initialBalance = atmHandler.getBalance();
		DepositRequest request = new DepositRequest();
		request.setAmount(200);
		Map<Integer,Integer> notesToDeposit = new HashMap<Integer, Integer>();
		notesToDeposit.put(10,4);
		notesToDeposit.put(20,3);
		notesToDeposit.put(50,2);
		request.setNotesToDeposit(notesToDeposit);

		int expectedBalance = initialBalance.getAmount()+request.getAmount();
		Map<Integer,Integer> initialBills = initialBalance.getBillsDetail().getBillsDetail();
		int expectedTenDollarNotes = initialBills.get(TEN_DOLLAR_KEY) + request.getNotesToDeposit().get(TEN_DOLLAR_KEY);
		int expectedTwentyDollarNotes = initialBills.get(TWENTY_DOLLAR_KEY) + request.getNotesToDeposit().get(TWENTY_DOLLAR_KEY);
		int expectedFiftyDollarNotes = initialBills.get(FIFTY_DOLLAR_KEY) + request.getNotesToDeposit().get(FIFTY_DOLLAR_KEY);

		atmHandler.updateBalance(request);
		Response updatedBalance = atmHandler.getBalance();
		Assert.assertEquals(updatedBalance.getAmount(), expectedBalance);
		Map<Integer,Integer> updatedBills = initialBalance.getBillsDetail().getBillsDetail();
		Assert.assertEquals((int)updatedBills.get(TEN_DOLLAR_KEY), expectedTenDollarNotes);
		Assert.assertEquals((int)updatedBills.get(TWENTY_DOLLAR_KEY), expectedTwentyDollarNotes);
		Assert.assertEquals((int)updatedBills.get(FIFTY_DOLLAR_KEY), expectedFiftyDollarNotes);
	}

	@Test
	public void testUpdateInvalidDeposit() {
		ATMHandler atmHandler = new ATMHandler();
		int balance = atmHandler.getBalance().getAmount();
		DepositRequest request = new DepositRequest();
		request.setAmount(200);
		Map<Integer,Integer> notesToDeposit = new HashMap<Integer, Integer>();
		notesToDeposit.put(10,2);
		notesToDeposit.put(20,3);
		notesToDeposit.put(50,1);
		notesToDeposit.put(100,1);
		request.setNotesToDeposit(notesToDeposit);

		Response response = atmHandler.updateBalance(request);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(),Status.INVALID);
		Assert.assertEquals(response.getMessage(),ResponseMessage.getResponseMessage(ResponseMessage.INVALID_DEPOSIT));
		Assert.assertEquals(response.getAmount(),0);
		Assert.assertEquals(atmHandler.getBalance().getAmount(),balance);
	}

	@Test
	public void testUpdateInvalidWithdrawal() {
		int withdrawalAmount = 275;
		ATMHandler atmHandler = new ATMHandler();
		int balance = atmHandler.getBalance().getAmount();
		Response response = atmHandler.withdrawAmount(withdrawalAmount);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(),Status.INVALID);
		Assert.assertEquals(response.getMessage(),ResponseMessage.getResponseMessage(ResponseMessage.INVALID_WITHDRAWAL));
		Assert.assertEquals(response.getAmount(),withdrawalAmount);
		Assert.assertEquals(atmHandler.getBalance().getAmount(),balance);
	}
}
