package com.hsbc.atm;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.atm.model.Response;
import com.hsbc.atm.model.WithdrawRequest;
import com.hsbc.atm.model.WithdrawResponse;

/**
 * ATMController
 * @author ayverma
 * All request for the app will land here and
 * then dispatched to corresponding path
 */
@RestController
public class ATMController {

	/** Logger instance for debug messages */
	private static final Logger LOGGER = Logger.getLogger(ATMController.class);

	/**
	 * withdrawRequest
	 * all withdrawal request comes here,
	 * responds with the status and balance
	 */
	@RequestMapping(value="/amount", method=RequestMethod.POST)
	public Response withdrawAmount(@RequestBody WithdrawRequest request) {
		LOGGER.debug("withdrawAmount - begins");
		return new WithdrawResponse();
	}
}