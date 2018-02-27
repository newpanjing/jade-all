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
 * <pre>
 * <ul>SQL语句注解
 * <li>填写被执行的SQL语句</li>
 * <li>支持参数
 *     <ul>参数定义的方式
 *     <li>参数模板，参数模板支持的占位符参考{@link #SQLParamType}</li>
 *     <li>参数列表，参数是加了这个注解的方法中的参数</li>
 *     <li>举例
 *         @SQL("Select * from Table Where Id = %d")
 *         void query(int id);
 *         底层解析时，会用String.format("Select * from Table Where Id = %d",id)，去拼接SQL
 *     <li>
 *     </ul>
 * </li>
 * </ul>
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SQL {

	String val();

	SQLType type();
}
