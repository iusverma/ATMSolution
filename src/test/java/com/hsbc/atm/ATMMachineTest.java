package com.hsbc.atm;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hsbc.enums.Status;

/**
 * Test class for ATM Machine
 * @author Ayush Verma
 */
public class ATMMachineTest {
	private static ATMMachine atm = new ATMMachine();
	private int balance = 100;

	@Test
	public void getBalance() {
		atm.setBalance(balance);
		Assert.assertEquals(atm.getBalance(), balance);
	}

	@Test
	public void getCurrencyToNotesMap() {
		Assert.assertTrue(atm.getCurrencyToNotesMap().isEmpty());
	}

	@Test
	public void isAmountAvailable() {
		Assert.assertTrue(atm.isAmountAvailable(balance));
	}

	@Test
	public void revertTransaction() {
		Assert.assertEquals(atm.revertTransaction(),Status.SUCCESS);
	}

	@Test
	public void setCurrencyToNotesMap() {
		Map<String, Integer> tempMap = new HashMap<>();
		tempMap.put("100",10);
		atm.setCurrencyToNotesMap(tempMap);
		Assert.assertEquals(atm.getCurrencyToNotesMap(), tempMap);
	}

	@Test
	public void withdrawAmount() {
		Assert.assertEquals(atm.withdrawAmount(balance), Status.SUCCESS);
	}
	
	/**
	 * Some scenarios to test
	 * 1. Have balance withdraw successful
	 * 2. Doesn't have balance withdraw rejected
	 * 3. Have balance in different denominations
	 * 4. Have balance but not sufficient notes.
	 */
}