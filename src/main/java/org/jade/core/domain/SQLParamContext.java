/**
 * 
 */
package org.jade.core.domain;

import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLParamContext {

	private SQL sqlAnnotation;
	
	private SQLParam[] methodParamAnnotations;
	
	private Object[] methodParams;

	/**
	 * @param sqlAnnotation
	 * @param methodParamAnnotations
	 * @param methodParams
	 */
	public SQLParamContext(SQL sqlAnnotation, SQLParam[] methodParamAnnotations, Object[] methodParams) {
		super();
		this.sqlAnnotation = sqlAnnotation;
		this.methodParamAnnotations = methodParamAnnotations;
		this.methodParams = methodParams;
	}

	public SQL getSqlAnnotation() {
		return sqlAnnotation;
	}

	public SQLParam[] getMethodParamAnnotations() {
		return methodParamAnnotations;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}
	
}
