/**
 * 
 */
package org.jade.util;

import java.util.ArrayList;
import java.util.List;

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
    private static final char CHAR_PERCENT = '%';
    private static final char[] UPPER = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final char[] LOWER = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

	public static List<SQLParamNode> findParamNodeFromSql(String sql) {
		char[] charArray = sql.toCharArray();
		List<SQLParamNode> paramNodeList = new ArrayList<>();
		
		List<Integer> percentIndexList = new ArrayList<>();
		for (int index = 0; index < charArray.length; index++) {
			if (charArray[index] == CHAR_PERCENT) {
				percentIndexList.add(index);
			}
		}
		
		if (percentIndexList.isEmpty()) {
			 return paramNodeList;
		}

		for (int index : percentIndexList) {
			String key = new String(new char[] { charArray[index], charArray[index + 1] });
			SQLParamType sqlParamType = SQLParamType.getSQLParamType(key);
			if(sqlParamType!=null){
				paramNodeList.add(new SQLParamNode(sqlParamType));
			}
		}
		
		return paramNodeList;
	}
	
	

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
        for(int index =0;index<num;index++){
            tabStr += TAB;
        }
        return tabStr;
    }

    public static String toUpperCaseAt0(String str){
        if(str==null){
            return "";
        }
        char[] chars = str.toCharArray();
        if(chars.length == 0){
            return "";
        }
        boolean isUpper = checkIsUpper(chars[0]);
        if(isUpper){
            return str;
        }
        chars[0] = (char) (chars[0]-32);
        return new String(chars);
    }

    public static String toLowerCaseAt0(String str){
        if(str==null){
            return "";
        }
        char[] chars = str.toCharArray();
        if(chars.length == 0){
            return "";
        }
        boolean isUpper = checkIsLower(chars[0]);
        if(isUpper){
            return str;
        }
        chars[0] = (char) (chars[0]+32);
        return new String(chars);
    }

    public static boolean checkIsUpper(char ch){
        for(char c : UPPER){
            if(ch == c){
                return true;
            }
        }
        return false;
    }

    public static boolean checkIsLower(char ch){
        for(char c :LOWER){
            if(ch == c){
                return true;
            }
        }
        return false;
    }

	public static void main(String[] args) {
		List<SQLParamNode> findParamFromSql = findParamNodeFromSql(" Select * from Account where id=%d and name = %s ");
		System.out.println(findParamFromSql);
	}
	
}
