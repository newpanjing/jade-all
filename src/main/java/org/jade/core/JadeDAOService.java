package org.jade.core;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jade.core.api.DAO;
import org.jade.core.invokehandler.JadeInvokeHandler;
import org.jade.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 *<pre>
 *<ul>
 *<B>DAO 服务<B>
 *<li>注册DAO服务，参见{@link #registerDao(String)}
 *<p>
 *加载指定包名下的所有加了DAO注解的接口，并且给这个接口动态绑定代理类<code>JadeInvokeHandler</code>
 *<p>
 *</li>
 *<li>
 *数据库交互交给{@link JadeInvokeHandler}处理
 *</li>
 *<li>提供DAO的获取,参见{@link #getDao(Class)}}</li>
 *</ul>
 *</pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
public class JadeDAOService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JadeDAOService.class);
	private static final Map<Class<?>, Object> DAO_INSTANCE_MAP = new HashMap<>();

	static void registerDao(String daoPackage) {
		List<Class<?>> findClassByPackagNameList = ClassUtil.findClassByPackagName(daoPackage);
		for (Class<?> clazz : findClassByPackagNameList) {
			if (clazz.isAnnotationPresent(DAO.class)) {
				JadeInvokeHandler invokehander = new JadeInvokeHandler();
				Object newProxyInstance = Proxy.newProxyInstance(JadeDAOService.class.getClassLoader(),
						new Class[] { clazz }, invokehander);
				DAO_INSTANCE_MAP.put(clazz, newProxyInstance);
				LOGGER.info("Register Dao --> {}", clazz);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> clazz) {
		Object object = DAO_INSTANCE_MAP.get(clazz);
		if (object == null) {
			return null;
		}
		return (T) object;
	}

}
