/**
 * 
 */
package org.jade.core.domain;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
	
	private Type[] actualTypeArguments ;
 
	public SQLParamContext(Method method, SQL sqlAnnotation, Object[] methodParams, List<SQLParam> sqlParamAnnoList,
			Type[] actualTypeArguments) {
		super();
		this.method = method;
		this.sqlAnnotation = sqlAnnotation;
		this.methodParams = methodParams;
		this.sqlParamAnnoList = sqlParamAnnoList;
		this.actualTypeArguments = actualTypeArguments;
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
	

	public Type[] getActualTypeArguments() {
		return actualTypeArguments;
	}

	public String methodParamToString() {
		StringBuilder sb = new StringBuilder();
		if (this.methodParams != null) {
			for (Object obj : this.methodParams) {
				sb.append(obj.toString() +" ");
			}
		}
		return sb.toString();
	}
}
