package com.kaynaak.rest.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	public static final String MD5 ="MD5";
	public static final String MD5_FORMAT_STRING ="%032x"; 
	
	public static String md5Hash(String password ){
		String hash = null;
		try {
			MessageDigest md5;
			md5 = MessageDigest.getInstance(MD5);
			md5.update(StandardCharsets.UTF_8.encode(password));
			hash= String.format(MD5_FORMAT_STRING, new BigInteger(1, md5.digest()));
		}catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return  hash;
 }
}
