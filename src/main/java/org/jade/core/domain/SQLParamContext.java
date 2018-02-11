/**
 * 
 */
package org.jade.core.domain;

import org.jade.core.api.SQL;

/**
 * 
 * <pre>
 * SQL语句参数的上下文,包含如下
 * <ul>
 * <li>SQL注解</li>
 * <li>加了SQL注解这个方法的所有参数</li> 
 * </ul>
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLParamContext {

	private SQL sqlAnnotation;

	private Object[] methodParams;

	/**
	 * @param sqlAnnotation
	 * @param methodParamAnnotations
	 * @param methodParams
	 */
	public SQLParamContext(SQL sqlAnnotation, Object[] methodParams) {
		super();
		this.sqlAnnotation = sqlAnnotation;
		this.methodParams = methodParams;
	}

	public SQL getSqlAnnotation() {
		return sqlAnnotation;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}

	public String methodParamToString() {
		StringBuilder sb = new StringBuilder();
		if (this.methodParams != null) {
			for (Object obj : this.methodParams) {
				sb.append(obj.toString());
			}
		}
		return sb.toString();
	}
}
