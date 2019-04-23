package com.chinasofti.bms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^1(3|5|7|8|4)\\d{9}");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
}
