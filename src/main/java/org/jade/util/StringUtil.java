/**
 * 
 */
package org.jade.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jade.core.constrant.SQLParamType;
import org.jade.core.domain.SQLParamNode;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class StringUtil {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	private static final String TAB = "\t";

	private static final char[] UPPER = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] LOWER = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

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

	/****
	 * 查询一个字符串 在另一个字符串出现的次数
	 * 
	 * @param source 源字符串
	 * @param regexStr 查找的字符串
	 * @return
	 */
	public static int findStrCount(String source, String regexStr) {
		String regex = "[^0-9a-zA-Z]"+regexStr+"[^0-9a-zA-Z]";
		Pattern expression = Pattern.compile(regex);
		Matcher matcher = expression.matcher(source);
		int n = 0;
		while (matcher.find()) {
			n++;
		}
		return n;
	}

	public static void main(String[] args) {
		// List<SQLParamNode> findParamFromSql = findParamNodeFromSql(" Select *
		// from Account where id=%d and name = %s ");
		// System.out.println(findParamFromSql);
		String format = String.format("Select * from Account where Id =  and id linke '%%'", 1, 1);
		System.out.println(format);
	}

}
