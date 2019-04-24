package com.chinasofti.bms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^1(3|5|7|8|4)\\d{9}");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	public static String format(String s,int min_length) {
		StringBuffer where = new StringBuffer();
		// 从待对齐的字符串中取出一段子字符串，子串的长度为行最大长度和s长度的较小值
		int wantedLength = Math.min(s.length(), min_length);
		String wanted = s.substring(0, wantedLength);
		// 如果是右对齐，那么将缺少的的字符用空格代替放在左边
        pad(where, min_length - wantedLength);
        // 将字符串添加在右边
        where.append(wanted);
		if (s.length() > wantedLength) {
			String remainStr = s.substring(wantedLength);
			where.append("\n" + format(remainStr,min_length));
		}
		return where.toString();
	}

	protected final static void pad(StringBuffer to, int howMany) {
		for (int i = 0; i < howMany; i++)
			to.append(" ");
	}

}
