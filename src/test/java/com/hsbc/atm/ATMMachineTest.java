package com.hsbc.atm;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hsbc.atm.model.DepositRequest;
import com.hsbc.enums.Status;

/**
 * Test class for ATM Machine
 * @author Ayush Verma
 */
public class ATMMachineTest {
	private static ATMMachine atm = ATMMachine.getInstance();

	@Test
	public void getCurrencyToNotesMap() {
		Assert.assertFalse(atm.getCurrencyToNotesMap().isEmpty());
	}

	@Test
	public void isAmountAvailable() {
		Assert.assertTrue(atm.isAmountAvailable(100));
	}

	@Test
	public void revertTransaction() {
		Assert.assertEquals(atm.revertTransaction(),Status.SUCCESS);
	}

	/**
	 * Following withdrawal will be tried from ATM
	 •	$20
	 •	$40
	 •	$50
	 •	$70
	 •	$80
	 •	$100
	 •	$150
	 •	$60
	 •	$110
	 */
	@Test(dataProvider="withdrawalDataProvider", priority=1)
	public void testWithdrawal(int amount, Status status) {
		int balance = atm.getBalance();
		Assert.assertEquals(atm.withdrawAmount(amount),status);
		Assert.assertEquals(atm.getBalance(), balance-amount);
	}

	@DataProvider(name="withdrawalDataProvider")
	public static Object[][] withdrawalDataProvider() {
		return new Object [][]{
			{new Integer(20),  Status.SUCCESS},
			{new Integer(40),  Status.SUCCESS},
			{new Integer(50),  Status.SUCCESS},
			{new Integer(70),  Status.SUCCESS},
			{new Integer(80),  Status.SUCCESS},
			{new Integer(100),  Status.SUCCESS},
			{new Integer(150),  Status.SUCCESS},
		};
	}

	/**
	 * $200, when there is only 3x$50 notes and 8x$20 notes available.
	 */
	@Test(priority=2)
	public void testCornerCase() {
		int balance = atm.getBalance();
		Assert.assertEquals(atm.withdrawAmount(balance),Status.SUCCESS);
		Assert.assertEquals(atm.getBalance(), 0);

		DepositRequest request = new DepositRequest();
		request.setAmount(310);
		Map<Integer,Integer> depositNotes = new HashMap<>();
		depositNotes.put(50,3);
		depositNotes.put(20,8);
		request.setNotesToDeposit(depositNotes);
		Assert.assertEquals(atm.depositAmount(request),Status.SUCCESS);
		Assert.assertEquals(atm.getBalance(), 310);

		Assert.assertEquals(atm.withdrawAmount(200),Status.SUCCESS);
		Assert.assertEquals(atm.getBalance(), 110);
	}
}