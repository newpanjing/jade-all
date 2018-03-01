/**
 * 
 */
package org.jade.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class StringUtil {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	private static final String TAB = "\t";

	private static final char[] UPPER = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] LOWER = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] DIGIT = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static String format(String template, Object... args) {
		return String.format(template, args);
	}

	public static String createSpaceStr(int num) {
		String spaceStr = "";
		for (int index = 0; index < num; index++) {
			spaceStr += SPACE;
		}
		return spaceStr;
	}

	public static String createEndLine(int num) {
		String endLineStr = "";
		for (int index = 0; index < num; index++) {
			endLineStr += END_LINE;
		}
		return endLineStr;
	}

	public static String createTab(int num) {
		String tabStr = "";
		for (int index = 0; index < num; index++) {
			tabStr += TAB;
		}
		return tabStr;
	}

	public static String toUpperCaseAt0(String str) {
		if (str == null) {
			return "";
		}
		char[] chars = str.toCharArray();
		if (chars.length == 0) {
			return "";
		}
		boolean isUpper = checkIsUpper(chars[0]);
		if (isUpper) {
			return str;
		}
		chars[0] = (char) (chars[0] - 32);
		return new String(chars);
	}

	public static String toLowerCaseAt0(String str) {
		if (str == null) {
			return "";
		}
		char[] chars = str.toCharArray();
		if (chars.length == 0) {
			return "";
		}
		boolean isUpper = checkIsLower(chars[0]);
		if (isUpper) {
			return str;
		}
		chars[0] = (char) (chars[0] + 32);
		return new String(chars);
	}

	public static boolean checkIsUpper(char ch) {
		for (char c : UPPER) {
			if (ch == c) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkIsLower(char ch) {
		for (char c : LOWER) {
			if (ch == c) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkIsDigit(char ch) {
		for (char c : DIGIT) {
			if (ch == c) {
				return true;
			}
		}
		return false;
	}

	/****
	 * 查询一个字符串 在另一个字符串出现的次数
	 * 
	 * @param source
	 *            源字符串
	 * @param regexStr
	 *            查找的字符串
	 * @return
	 */
	public static int findStrCount(String source, String regexStr) {
		String regex = "\\b" + regexStr + "\\b";
		Pattern expression = Pattern.compile(regex);
		Matcher matcher = expression.matcher(source);
		int n = 0;
		while (matcher.find()) {
			n++;
		}
		return n;
	}

	public static void main(String[] args) {

		String source = " Update Account set name = #NAME# where id = ID ";
		System.out.println(findStrCount(source, "NAME"));
	}

}
