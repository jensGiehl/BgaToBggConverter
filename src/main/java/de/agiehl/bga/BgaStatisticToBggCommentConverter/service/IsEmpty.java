package de.agiehl.bga.BgaStatisticToBggCommentConverter.service;

public class IsEmpty {

	public static boolean isEmpty(String str) {
		return str.isBlank() || "-".equals(str) || "0".equals(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
