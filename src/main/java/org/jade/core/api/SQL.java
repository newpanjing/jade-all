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
 *     <li>方法参数列表中，参数将被{@link PreparedStatement}调用setXXX方法，按方法参数列表中的顺序进行参数设置</li>
 *     <li>举例
 *         @SQL("Select * from Table Where Id = ?")
 *         void query(int id);
 *       
 *         假设调用 query(6);
 *         底层处理：
 *         PreparedStatement pre;
 *         pre.setInt(1, 6); 
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
