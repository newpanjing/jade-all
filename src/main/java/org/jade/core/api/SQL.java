/**
 * 
 */
package org.jade.core.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jade.core.constrant.SQLType;

/**
 * 
 * 具有执行SQL语句的能力
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@Target(value=ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SQL {
	
	String val();
	
	SQLType type();
}
