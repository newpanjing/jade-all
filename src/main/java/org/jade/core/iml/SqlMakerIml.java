/**
 * 
 */
package org.jade.core.iml;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;

import org.jade.core.api.ISqlMaker;
import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;
import org.jade.core.domain.SQLParamContext;
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

		if (methodParams != null) {
			HashSet<String> checkRepeateSQLParam = new HashSet<>();

			for (SQLParam param : sqlParamAnnoList) {
				if (param.value().isEmpty()) {
					throw new SQLMakeException(String.format("SQLParam 注解的值不能为空,method = %s ,sql = %s", method, sql));
				}
				if (checkRepeateSQLParam.contains(param.value())) {
					throw new SQLMakeException(String.format("SQLParam 注解的值不能重复，method = %s , SQLParam.value = %s",
							method, param.value()));
				}
				if (!checkParam(param.value())) {
					throw new SQLMakeException(
							String.format("SQLParam 注解的值约定为字母，method = %s,SQLParam.value = %s", method, param.value()));
				}
				int findStrCount = StringUtil.findStrCount(sql, param.value());
				if (findStrCount == 0) {
					throw new SQLMakeException(
							String.format("SQL注解中,sql语句的找不到参数(%s),请与SQLParam注解的值保持一致.sql = %s,method = %s",
									param.value(), sql, method));
				}
				if (findStrCount > 1) {
					throw new SQLMakeException(
							String.format("SQL注解中,sql语句的参数重复. 参数(%s)请用其他的值替换,与SQLParam注解的值保持一致.sql = %s,method = %s",
									param.value(), sql, method));
				}
				checkRepeateSQLParam.add(param.value());
			}
			for (int index = 0; index < sqlParamAnnoList.size(); index++) {
				SQLParam sp = sqlParamAnnoList.get(index);
				sql = sql.replace(sp.value(), methodParams[index].toString());
			}
		}
		return sql;
	}

	/***
	 * SQL参数的value约定为字母
	 * 
	 * @param param
	 * @return
	 */
	private static boolean checkParam(String param) {
		char[] charArray = param.toCharArray();
		for (char ch : charArray) {
			if (!(StringUtil.checkIsLower(ch) || StringUtil.checkIsUpper(ch))) {
				return false;
			}
		}
		return true;
	}
}
