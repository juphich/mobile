package com.wise.core;

import java.util.Random;

public class SequenceGenerator {

	private static char[] CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public static String next() {
		return next("");
	}
	
	public static String next(String prefix) {
		StringBuilder key = new StringBuilder();
		if (!prefix.isEmpty()) {
			key.append(prefix).append("-");
		}
		
		key.append(String.valueOf(System.currentTimeMillis())).append("-");
		
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
            int index = random.nextInt(25);
            key.append(CODE[index]);
        }
        
		return key.toString();
	}
}