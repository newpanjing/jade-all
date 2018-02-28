/**
 * 
 */
package org.jade.core.domain;

import java.lang.reflect.Method;
import java.util.List;

import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;

/**
 * 
 * <pre>
 * <b>SQL语句参数的上下文,包含如下</b>
 * <ul>
 * <li>代理类执行的方法</li>
 * <li>SQL注解</li>
 * <li>加了SQL注解这个方法的所有参数</li>
 * <li>方法参数的注解</li> 
 * </ul>
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLParamContext {

	private Method method;
	
	private SQL sqlAnnotation;

	private Object[] methodParams;
	
	private List<SQLParam> sqlParamAnnoList;


	public SQLParamContext(Method method,SQL sqlAnnotation, Object[] methodParams, List<SQLParam> sqlParamAnnoList) {
		super();
		this.sqlAnnotation = sqlAnnotation;
		this.methodParams = methodParams;
		this.sqlParamAnnoList = sqlParamAnnoList;
		this.method = method;
	}

	public SQL getSqlAnnotation() {
		return sqlAnnotation;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}

	public List<SQLParam> getSqlParamAnnoList() {
		return sqlParamAnnoList;
	}

	public Method getMethod() {
		return method;
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
