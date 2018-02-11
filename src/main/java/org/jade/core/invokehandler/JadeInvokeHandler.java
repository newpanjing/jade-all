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
import org.jade.core.domain.SQLParamContext;
import org.jade.core.exception.SQLMakeException;
import org.jade.core.iml.SqlMakerIml;
import org.jade.db.DataSourceService;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class JadeInvokeHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws SQLMakeException {
		Class<?> returnType = method.getReturnType();
		Type genericReturnType = method.getGenericReturnType();
		Type[] actualTypeArguments = null;
		if (genericReturnType instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) genericReturnType;
			actualTypeArguments = pt.getActualTypeArguments();
		}

		Annotation[] annotations = method.getAnnotations();

		SQL sqlAnnotation = null;

		for (Annotation annotation : annotations) {
			if (annotation instanceof SQL) {
				sqlAnnotation = (SQL) annotation;
			}
		}
		if (sqlAnnotation == null) {
			throw new SQLMakeException(String.format("%s的 %s方法,未加@SQL注解", proxy, method.getName()));
		}
		SQLParamContext methodParamNode = new SQLParamContext(sqlAnnotation, args);
		try {
			String sql = SqlMakerIml.INSTANCE.make(methodParamNode);
			return DataSourceService.execute(sqlAnnotation.type(), sql, returnType, actualTypeArguments);
		} catch (SQLMakeException ex) {
			throw ex;
		}
	}

}
