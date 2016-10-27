package com.heibaba.common.utils;

import java.util.Random;

public class RandomNumUtil {

	private final static String[] NUMCHAR = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9" };

	public static String genNum(int length) {
		StringBuilder sb = new StringBuilder();

		Random rand = new Random();
		int pos = 0;
		while (pos++ < length) {
			sb.append(NUMCHAR[rand.nextInt(NUMCHAR.length)]);
		}

		return sb.toString();
	}
}
