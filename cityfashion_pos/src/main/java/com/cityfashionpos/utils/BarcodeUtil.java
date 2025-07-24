package com.cityfashionpos.utils;

public class BarcodeUtil {

	private static final String PREFIX = "CFK";
	private static final int DIGITS = 6;

	public static String generateFromId(Long id) {
		return PREFIX + String.format("%0" + DIGITS + "d", id);
	}

}
