package com.hsbc.atm;

import com.hsbc.atm.model.Response;
import com.hsbc.atm.util.ResponseFactory;
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
		Response response = ResponseFactory.getResponseFromStatus(status);
		response.setBalance(atmMachine.getBalance());
		response.setAmountRequested(amount);
		return response;
	}

	/**
	 * Returns balance in ATM
	 * @return
	 */
	public Response getBalance() {
		Response response = ResponseFactory.getResponseFromStatus(Status.SUCCESS);
		response.setBalance(atmMachine.getBalance());
		response.setAmountRequested(0);
		return response;
	}
	
	/**
	 * Will return status of each and every note inside status
	 * In future release, response will contain list of notes
	 * and their remaining counts.
	 */
	public Response getATMStatus() {
		Response response = ResponseFactory.getResponseFromStatus(Status.SUCCESS);
		response.setBalance(atmMachine.getBalance());
		response.setAmountRequested(0);
		return response;
	}
}
