package com.baeldung.spring.cloud.eureka.client.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class EncriptDecriptUtil {

	private static String secretKey = "CRYPTOCRYPTO";

	public static String encrypt(String stringToEncrypt) throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		byte[] KeyData = secretKey.getBytes();
		SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, KS);
		return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes("UTF-8")));
	}

	public static String decrypt(String stringToDecrypt) throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {

		byte[] KeyData = secretKey.getBytes();
		SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, KS);
		return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
	}
}
