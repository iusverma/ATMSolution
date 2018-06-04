package com.hsbc.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ATApp
 * @author Ayush Verma
 * Main application to start up the service
 */
@SpringBootApplication
public class ATMApp {
	public static void main(String...args) {

		/**
		 * Main method, will start the ATM app
		 */
		SpringApplication.run(ATMApp.class);
	}
}
