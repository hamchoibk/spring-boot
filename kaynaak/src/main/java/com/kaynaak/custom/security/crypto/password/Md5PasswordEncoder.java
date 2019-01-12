package com.kaynaak.custom.security.crypto.password;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.kaynaak.rest.util.Md5Util;

public class Md5PasswordEncoder  implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return Md5Util.md5Hash(rawPassword.toString());
		//return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
		
		String hashPwd =Md5Util.md5Hash(rawPassword.toString());
		return encodedPassword.equals(hashPwd);
	}

}
