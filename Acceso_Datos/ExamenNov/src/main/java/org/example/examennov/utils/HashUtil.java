package org.example.examennov.utils;

import java.security.MessageDigest;
import java.util.HexFormat;

public class HashUtil {
	public HashUtil(){}
	public static String sha256(String input) {
		try {
			return HexFormat.of().formatHex(
					MessageDigest.getInstance("SHA-256").digest(input.getBytes())
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
