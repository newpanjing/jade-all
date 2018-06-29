/**
 * 
 */
package org.jade.core.domain;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;

import org.jade.core.api.IResultSetExtracter;
import org.jade.core.api.ISQLExecuter;


/**
 * <pre>
 * 提供{@link ISQLExecuter#execute(SQLExecuterParam)}的参数
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLExecuterContext {
	private PreparedStatement statement;

	private String sql;

	private Class<?> returnType;

	private Type[] actualTypeArguments;
	
	@SuppressWarnings("rawtypes")
	private Class<? extends IResultSetExtracter> exracter;

	private Class<?> daoClass; 
	
	private String methodName;

	@SuppressWarnings("rawtypes")
	public SQLExecuterContext(PreparedStatement statement, String sql, Class<?> returnType, Type[] actualTypeArguments,
			Class<? extends IResultSetExtracter> exracter, Class<?> daoClass, String methodName) {
		super();
		this.statement = statement;
		this.sql = sql;
		this.returnType = returnType;
		this.actualTypeArguments = actualTypeArguments;
		this.exracter = exracter;
		this.daoClass = daoClass;
		this.methodName = methodName;
	}

	public PreparedStatement getStatement() {
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

	@SuppressWarnings("rawtypes")
	public Class<? extends IResultSetExtracter> getExracter() {
		return exracter;
	}

	public Class<?> getDAOClass() {
		return daoClass;
	}

	public Class<?> getDaoClass() {
		return daoClass;
	}

	public String getMethodName() {
		return methodName;
	}

	
	 
}
