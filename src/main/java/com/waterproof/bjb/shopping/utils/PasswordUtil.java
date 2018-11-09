package com.waterproof.bjb.shopping.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordUtil {

	public static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	public static String getPassword(String password) {
		String strong_salt = "$2a$10$NnlqpBH.dJZLqG//0IWoG.";
		log.info("strong_salt {}, pw: {}", strong_salt, BCrypt.hashpw(password, strong_salt));
		return  BCrypt.hashpw(password, strong_salt);
	}
	
}
