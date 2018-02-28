/**
 * 
 */
package org.jade.core.iml;

import java.lang.reflect.Method;
import java.util.List;

import org.jade.core.api.ISqlMaker;
import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;
import org.jade.core.domain.SQLParamContext;
import org.jade.core.domain.SQLParamNode;
import org.jade.core.exception.SQLMakeException;
import org.jade.util.StringUtil;

/**
 * <pre>
 * SQL语句组装~
 * </pre>
 * 
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
		List<SQLParam> sqlParamAnnoList = context.getSqlParamAnnoList();
		Method method = context.getMethod();
		String sql = sqlAnnotation.val();

		for (SQLParam param : sqlParamAnnoList) {
			if (param.value().isEmpty()) {
				throw new SQLMakeException(String.format("SQLParam 注解的值不能为空,method = %s ,sql = %s", method, sql));
			}
			int findStrCount = StringUtil.findStrCount(sql, param.value());
			if (findStrCount > 1) {
				throw new SQLMakeException(
						String.format("SQL注解中,sql语句的参数重复. 参数(%s)请用其他的值替换,与SQLParam注解的值保持一致.sql = %s,method = %s",
								param.value(), sql, method));
			}
			if(findStrCount == 0){
				throw new SQLMakeException(
						String.format("SQL注解中,sql语句的找不到参数(%s),请与SQLParam注解的值保持一致.sql = %s,method = %s",
								param.value(), sql, method));
			}
		}

		if (!sqlParamAnnoList.isEmpty() && methodParams == null) {
			throw new SQLMakeException(String.format("SQL语句make失败，模板参数个数=%d,传入的参数个数=%d.模板=%s,参数列表=%s",
					sqlParamAnnoList.size(), 0, sql, context.methodParamToString()));
		}
		int methodParamNum = 0;
		if (methodParams != null) {
			for (int index = 0; index < methodParams.length; index++) {
				if (methodParams != null) {
					methodParamNum++;
				}
			}
			for (Object param : methodParams) {

			}
			// if (findParamNodeFromSql.size() != methodParamNum) {
			// throw new
			// SQLMakeException(String.format("SQL语句make失败，模板参数个数=%d,传入的参数个数=%d.模板=%s,参数列表=%s",
			// findParamNodeFromSql.size(), methodParamNum, sql,
			// context.methodParamToString()));
			// }
		}

		return String.format(sql, methodParams);
	}

}
