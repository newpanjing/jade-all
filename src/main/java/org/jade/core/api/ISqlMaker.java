/**
 * 
 */
package org.jade.core.api;

import org.jade.core.domain.SQLParamContext;
import org.jade.core.exception.SQLMakeException;

/**
 * <pre>
 * 具有根据方法的一些属性，就可以拼装出SQL语句的抽象
 * </pre>
 * 
 * 
 * 
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
public interface ISqlMaker {
	
	String make(SQLParamContext context) throws SQLMakeException;
	
}
