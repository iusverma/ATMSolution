package com.hsbc.atm.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * BillsDetails class
 * @author Ayush Verma
 * This class contain details of dollar bills
 * in response along with total value of it
 */
public class BillsDetail {
	/** Total value of dollar bills */
	private int totalAmount;

	/** Details for dollar bills */
	private Map<Integer, Integer> billsDetail;

	/** Default constructor */
	public BillsDetail() {
		this.billsDetail = new HashMap<>();
		this.totalAmount = evaluateTotalAmount();
	}

	/** Constructor with hash map */
	public BillsDetail(Map<Integer, Integer> billsDetail) {
		this.billsDetail = billsDetail;
		this.totalAmount = evaluateTotalAmount();
	}

	/**
	 * Getter for total amount
	 */
	public int getTotalAmount() {
		return evaluateTotalAmount();
	}

	/**
	 * Setter for total amount
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * Getter for billsDetail
	 */
	public Map<Integer, Integer> getBillsDetail() {
		return billsDetail;
	}

	/**
	 * Getter for billsDetail
	 */
	public void setBillsDetail(Map<Integer, Integer> billsDetail) {
		this.billsDetail = billsDetail;
	}

	/**
	 * Calculate total amount
	 */
	private int evaluateTotalAmount() {
		int total = 0;
		Set<Integer> keySet = billsDetail.keySet();
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			int value = billsDetail.get(key);
			total += value*key;
		}
		this.totalAmount = total;
		return this.totalAmount;
	}
}
