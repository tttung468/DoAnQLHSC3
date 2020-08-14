package com.toanhuuvuong.utils;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtils
{
	private static final int LOG_ROUNDS_DEFAULT = 12;
	
	public static String encrypt(String plaintext)
	{
		return BCrypt.hashpw(plaintext, BCrypt.gensalt(LOG_ROUNDS_DEFAULT));
	}
	public static boolean compare(String plaintext, String hashed)
	{
		return BCrypt.checkpw(plaintext, hashed);
	}
}
