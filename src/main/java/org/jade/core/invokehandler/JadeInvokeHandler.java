/**
 * 
 */
package org.jade.core.invokehandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.jade.core.api.SQL;
import org.jade.core.constrant.SQLType;
import org.jade.db.DataSourceService;

/**
 * @author Jack Lei
 * @Time 2018年2月5日 下午7:14:49
 * @Email 895896736@qq.com
 */

public class JadeInvokeHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		 
		Type genericReturnType = method.getGenericReturnType();
		if(genericReturnType instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType) genericReturnType;
			Type[] actualTypeArguments = pt.getActualTypeArguments();
			for(Type type : actualTypeArguments){
				 System.out.println(" return gen type "+ type);
			}
		}
		 
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if(annotation instanceof SQL){
				SQL sql = (SQL)annotation;
				String sqlVal = sql.sql();
				SQLType type = sql.type();
				DataSourceService.query(sqlVal, Object.class);
				System.out.println("sqlVal "+sqlVal);
			}
		}
		return null;
	}

}
