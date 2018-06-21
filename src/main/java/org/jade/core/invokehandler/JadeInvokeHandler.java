/**
 * 
 */
package org.jade.core.invokehandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;
import org.jade.core.domain.SQLParamContext;
import org.jade.core.exception.SQLMakeException;
import org.jade.db.DataSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *<ul>
 *数据库接口代理类
 *<li>
 *拼装SQL语句,参见{@link#ISqlMaker}
 *</li>
 *<li>
 *将应用层请求的数据库服务，给{@link#DataSourceService}的execute方法处理
 *</li>
 *</ul>
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class JadeInvokeHandler implements InvocationHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(JadeInvokeHandler.class);

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		Type genericReturnType = method.getGenericReturnType();
		// 方法返回类型的泛型数组
		Type[] actualTypeArguments = null;
		// 判断方法的返回类型是否是泛型
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
			LOGGER.error("{}的{}方法,未加@SQL注解", proxy, method.getName());
		}

		// 获取方法参数里的注解
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		List<SQLParam> sqlParamAnnoList = new ArrayList<>();
		if (parameterAnnotations != null) {
			for (Annotation[] annoArray : parameterAnnotations) {
				for (Annotation anno : annoArray) {
					if (anno instanceof SQLParam) {
						sqlParamAnnoList.add((SQLParam) anno);
					}
				}
			}
		}

		SQLParamContext context = new SQLParamContext(method, sqlAnnotation, args, sqlParamAnnoList,actualTypeArguments);
		return DataSourceService.execute(context);
	}

}
