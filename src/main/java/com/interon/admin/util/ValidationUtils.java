package com.interon.admin.util;

import java.util.List;

public class ValidationUtils {

	public static boolean regexValidate(String str, String regex) {
		return str.matches(regex);
	}
	
	public static boolean isNullString(String str) {
		return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
	}
	
	public static boolean isStringLengthMoreThan25(String str) {
		return str.length()>25;
	}
	
	public static boolean isStringLengthMoreThan50(String str) {
		return str.length()>50;
	}
	
	public static boolean isStringLengthNot10(String str) {
		return str.length()!=10;
	}
	
	public static boolean isStringLength5or6(String str) {
		return str.length()==5 || str.length()==6;
	}

	public static boolean isEmptyList(List list) {
		return list.isEmpty();
	}
}
