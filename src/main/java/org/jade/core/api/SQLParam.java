package org.jade.core.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/****
 * <pre>
 * <ul>
 * SQL参数注解
 * <li>用来支持动态参数的SQL语句</li>
 * </ul>
 * </pre>
 *	@author Jack Lei
 * @Email 895896736@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface SQLParam {
	
	/***序号*/
	int index() ;
}
