package com.hsbc.atm;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.atm.model.Request;
import com.hsbc.atm.model.Response;

/**
 * ATMController
 * @author Ayush Verma
 * All request for the application will land here and
 * then dispatched to corresponding path
 */
@RestController
public class ATMController {

	/** Logger instance for debug messages */
	private static final Logger LOGGER = Logger.getLogger(ATMController.class);

	/**
	 * ATMHandler for all operations */
	private ATMHandler atmHandler = new ATMHandler();

	/**
	 * Withdraw Request
	 * all withdrawal request comes here,
	 * responds with the status and balance
	 */
	@RequestMapping(value="/amount", method=RequestMethod.POST)
	public Response withdrawAmount(@RequestBody Request request) {
		LOGGER.debug("withdrawAmount - begins");
		return atmHandler.withdrawAmount(request.getAmount());
	}

	/**
	 * Withdraw Request
	 * all withdrawal request comes here,
	 * responds with the status and balance
	 */
	@RequestMapping(value="/balance", method=RequestMethod.GET)
	public Response withdrawAmount() {
		LOGGER.debug("withdrawAmount - begins");
		return atmHandler.getBalance();
	}
}
