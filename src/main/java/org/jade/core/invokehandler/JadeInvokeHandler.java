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
import org.jade.core.api.SQLParam;
import org.jade.core.domain.SQLParamContext;

/**
 * @author Jack Lei
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
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		
		SQL sqlAnnotation = null;
		
		for (Annotation annotation : annotations) {
			if(annotation instanceof SQL){
				sqlAnnotation = (SQL)annotation;
			}
		}
		
		
		int length = parameterAnnotations.length;
		SQLParam[] sqlParamList = new SQLParam[length];
		for(int i = 0 ;i< length;i++){
			Annotation[] anoArray  = parameterAnnotations[i];
			for(Annotation an :anoArray){
				if(an instanceof SQLParam){
					sqlParamList[i] = (SQLParam)an;
				}
			}
		}
		
		SQLParamContext methodParamNode = new SQLParamContext(sqlAnnotation,sqlParamList, args);
		 
		return null;
	}

}
