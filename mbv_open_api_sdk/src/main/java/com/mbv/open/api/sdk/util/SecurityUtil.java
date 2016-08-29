package com.mbv.open.api.sdk.util;

import java.security.MessageDigest;

public class SecurityUtil {

	public static String md5(String source) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(source.getBytes("utf-8"));
		return byte2hex(bytes);
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

}
