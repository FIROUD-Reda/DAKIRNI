package com.example.dakirni.Crypto;

import java.security.SecureRandom;
import java.util.Random;


public class Utils {

	private final Random random = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generateCarId(int length) {
		StringBuilder returnVlue = new StringBuilder(length);
		
		for(int i=0 ; i< length ; i++) {
			returnVlue.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
			
		}
		return new String(returnVlue);
	}
	
	
}
