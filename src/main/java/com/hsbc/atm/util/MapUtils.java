package com.hsbc.atm.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * MapUtils class
 * @author Ayush Verma
 * Different utility functions on Maps
 */
public class MapUtils {
	/**
	 * Returns sum of all elements inside a given map
	 */
	public static int sumOfMapElements(Map<Integer,Integer> map) {
		int sum = 0;
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			int value = map.get(key);
			sum += value*key;
		}
		return sum;
	}
}
