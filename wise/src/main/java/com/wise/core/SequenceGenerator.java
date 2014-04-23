package com.wise.core;

import java.util.Random;

public class SequenceGenerator {

	private static char[] CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public static String next() {
		StringBuilder key = new StringBuilder();
		key.append(String.valueOf(System.currentTimeMillis())).append("-");
		
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
            int index = random.nextInt(25);
            key.append(CODE[index]);
        }
        
		return key.toString();
	}
}