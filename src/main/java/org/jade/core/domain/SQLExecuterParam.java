/**
 * 
 */
package org.jade.core.domain;

import java.lang.reflect.Type;
import java.sql.Statement;

import org.jade.core.api.ISQLExecuter;

/**
 * <pre>
 * 提供{@link ISQLExecuter#execute(SQLExecuterParam)}的参数
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLExecuterParam {
	private Statement statement;
	
	private String sql;
	
	private Class<?> returnType;
	
	private Type[] actualTypeArguments;

	public SQLExecuterParam(Statement statement, String sql, Class<?> returnType, Type[] actualTypeArguments) {
		super();
		this.statement = statement;
		this.sql = sql;
		this.returnType = returnType;
		this.actualTypeArguments = actualTypeArguments;
	}

	public Statement getStatement() {
		return statement;
	}

	public String getSql() {
		return sql;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public Type[] getActualTypeArguments() {
		return actualTypeArguments;
	} 

}
