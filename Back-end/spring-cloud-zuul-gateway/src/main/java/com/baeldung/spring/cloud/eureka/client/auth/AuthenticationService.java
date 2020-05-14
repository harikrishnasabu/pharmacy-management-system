package com.baeldung.spring.cloud.eureka.client.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AuthenticationService {

	private static final String SPLITER = "XYZ";
	private static final int TOKEN_LIFE_IN_HOUR = 1;

	public String generateAuthenticationTocken(String userName, String password)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, TOKEN_LIFE_IN_HOUR);
		long timeAddedTockenLife = cal.getTime().getTime();
		String tockenToEncrypt = userName + SPLITER + password + SPLITER + timeAddedTockenLife;
		return EncriptDecriptUtil.encrypt(tockenToEncrypt);
	}

	public boolean validateToken(String autheticationToken) throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		if (null != autheticationToken && !autheticationToken.isEmpty()) {
			String decryptedTocken = EncriptDecriptUtil.decrypt(autheticationToken);
			String[] decryptedValueParts = decryptedTocken.split(SPLITER);
			long tockenLifeTime = Long.parseLong(decryptedValueParts[2]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			long currentTime = cal.getTime().getTime();
			return Long.compare(tockenLifeTime, currentTime) > 0;
		}
		return false;
	}

}
