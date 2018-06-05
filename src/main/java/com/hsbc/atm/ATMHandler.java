package com.hsbc.atm;

import com.hsbc.atm.model.BillsDetail;
import com.hsbc.atm.model.DepositRequest;
import com.hsbc.atm.model.Response;
import com.hsbc.atm.util.MapUtils;
import com.hsbc.atm.util.ResponseFactory;
import com.hsbc.enums.OperationType;
import com.hsbc.enums.Status;

/**
 * ATMHandler
 * @author Ayush Verma
 * ATM handler, this class with interact with ATM class
 * and perform all required operations
 */
public class ATMHandler {
	private static ATMMachine atmMachine = ATMMachine.getInstance();

	/**
	 * Evaluates the balance and request for withdrawal
	 */
	public Response withdrawAmount(int amount) {
		Status status = Status.FAILED;
		if(atmMachine.isAmountAvailable(amount)) {
			status = atmMachine.withdrawAmount(amount);
		}else {
			status = Status.INSUFFICIENT;
		}
		Response response = ResponseFactory.getResponseFromStatus(status,OperationType.WITHDRAWAL);
		response.setAmount(amount);
		response.setBillsDetail(new BillsDetail(atmMachine.getCurrentTransactionHoldings()));
		return response;
	}

	/**
	 * Returns balance in ATM
	 * @return
	 */
	public Response getBalance() {
		Response response = ResponseFactory.getResponseFromStatus(Status.SUCCESS, OperationType.ENQUIRY);
		response.setAmount(atmMachine.getBalance());
		response.setBillsDetail(new BillsDetail(atmMachine.getCurrencyToNotesMap()));
		return response;
	}

	/**
	 * Deposit the amount in ATM machine
	 */
	public Response updateBalance(DepositRequest request) {
		Status status = Status.INVALID;
		if(request==null || !isRequestValid(request)) {
			status = atmMachine.depositAmount(request);
			Response response = ResponseFactory.getResponseFromStatus(status, OperationType.DEPOSIT);
			return response;
		}
		status = atmMachine.depositAmount(request);
		Response response = ResponseFactory.getResponseFromStatus(status, OperationType.DEPOSIT);
		response.setAmount(request.getAmount());
		response.setBillsDetail(new BillsDetail(atmMachine.getCurrencyToNotesMap()));
		return response;
	}

	/**
	 * Validates DepositRequests
	 * Checks whether amount requested and number of bills are in sync
	 */
	private boolean isRequestValid(DepositRequest request) {
		if(request.getAmount() ==  MapUtils.sumOfMapElements(request.getNotesToDeposit()))
			return true;
		return false;
	}
}
