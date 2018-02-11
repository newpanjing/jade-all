/**
 * 
 */
package org.jade.core.iml;

import java.util.List;

import org.jade.core.api.ISqlMaker;
import org.jade.core.api.SQL;
import org.jade.core.domain.SQLParamContext;
import org.jade.core.domain.SQLParamNode;
import org.jade.core.exception.SQLMakeException;
import org.jade.util.StringUtil;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SqlMakerIml implements ISqlMaker {

	public static final SqlMakerIml INSTANCE = new SqlMakerIml();

	private SqlMakerIml() {
	}

	@Override
	public String make(SQLParamContext context) throws SQLMakeException {
		Object[] methodParams = context.getMethodParams();
		SQL sqlAnnotation = context.getSqlAnnotation();
		String sql = sqlAnnotation.val();
		List<SQLParamNode> findParamNodeFromSql = StringUtil.findParamNodeFromSql(sql);
		if (!findParamNodeFromSql.isEmpty() && methodParams == null) {
			throw new SQLMakeException(String.format("SQL语句make失败，模板参数个数=%d,传入的参数个数=%d.模板=%s,参数列表=%s",
					findParamNodeFromSql.size(), 0, sql, context.methodParamToString()));
		}
		int methodParamNum = 0;
		if (methodParams != null) {
			for (int index = 0; index < methodParams.length; index++) {
				if (methodParams != null) {
					methodParamNum++;
				}
			}
		}
		if (findParamNodeFromSql.size() != methodParamNum) {
			throw new SQLMakeException(String.format("SQL语句make失败，模板参数个数=%d,传入的参数个数=%d.模板=%s,参数列表=%s",
					findParamNodeFromSql.size(), methodParamNum, sql, context.methodParamToString()));
		}
		return String.format(sql, methodParams);
	}

}
