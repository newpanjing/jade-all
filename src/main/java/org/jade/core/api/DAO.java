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
 * 
 * <pre>
 * <ul>
 * <b>DAO服务：抽象了应用层与数据库交互的<b>
 * <li>
 *在方法添加{@link SQL}}注解，具有SQL语句的执行能力 
 * </li>
 * <li>
 *Jade启动时会注册,加了DAO的接口
 * </li>
 * </ul>
 * </pre>
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@Target(value=ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DAO {
	
}
