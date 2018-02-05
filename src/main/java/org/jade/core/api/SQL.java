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
 * @author Jack Lei
 * @Time 2018年2月5日 下午7:24:55
 * @Email 895896736@qq.com
 */
@Target(value=ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SQL {
	
	String sql();
	
	SQLType type();
}
