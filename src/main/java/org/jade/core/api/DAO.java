/**
 * 
 */
package org.jade.core.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 具有SQL语句的执行能力
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@Target(value=ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DAO {
	
}
