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
 * DAO 服务
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
