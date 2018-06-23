/**
 * 
 */
package org.jade.core.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jade.core.iml.SQLQueryExecuter;

/**标记为数据库表的映射类，目前用于将数据库对象转成java对象的判断条件，参考{@link SQLQueryExecuter#execute(org.jade.core.domain.SQLExecuterContext)}
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Bean {

}
