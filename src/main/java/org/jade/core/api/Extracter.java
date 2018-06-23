/**
 * 
 */
package org.jade.core.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**提取器
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Extracter {
	
	@SuppressWarnings("rawtypes")
	Class<? extends IResultSetExtracter > extracter();
	
}
