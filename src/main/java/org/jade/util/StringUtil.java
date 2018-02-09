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
	private static final char CHAR_PERCENT = '%';

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

	public static void main(String[] args) {
		List<SQLParamNode> findParamFromSql = findParamNodeFromSql(" Select * from Account where id=%d and name = %s ");
		System.out.println(findParamFromSql);
	}

}
