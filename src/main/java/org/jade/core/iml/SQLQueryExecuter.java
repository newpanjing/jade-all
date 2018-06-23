/**
 * 
 */
package org.jade.core.iml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jade.core.api.Bean;
import org.jade.core.api.IResultSetExtracter;
import org.jade.core.api.ISQLExecuter;
import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterContext;
import org.jade.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * SQL语句查询执行器
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLQueryExecuter implements ISQLExecuter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SQLQueryExecuter.class);
	public static final SQLQueryExecuter INSTANCE = new SQLQueryExecuter();

	private SQLQueryExecuter() {

	}

	@Override
	public SQLType getType() {
		return SQLType.SELECT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object execute(SQLExecuterContext param) throws SQLException {
		PreparedStatement statement = param.getStatement();
		Class<?> returnType = param.getReturnType();
		Type[] actualTypeArguments = param.getActualTypeArguments();
		ResultSet resultSet = null;

		resultSet = statement.executeQuery();
		Class<? extends IResultSetExtracter> exracter = param.getExracter();

		// 优先处理用户自定义的解析器
		if (exracter != null) {
			try {
				IResultSetExtracter newInstance = exracter.newInstance();
				return newInstance.extract(resultSet);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 处理基本数据类型，bean对象，list
		if (returnType == Integer.class || returnType == int.class) {
			if (resultSet.next()) {

				return resultSet.getInt(1);
			}
			return null;
		} else if (returnType == Float.class || returnType == float.class) {
			if (resultSet.next()) {
				return resultSet.getFloat(1);
			}
			return null;
		} else if (returnType == Long.class || returnType == long.class) {
			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
			return null;
		} else if (returnType == Double.class || returnType == double.class) {
			if (resultSet.next()) {
				return resultSet.getDouble(1);
			}
			return null;
		} else if (returnType == Short.class || returnType == short.class) {
			if (resultSet.next()) {
				return resultSet.getShort(1);
			}
			return null;
		} else if (returnType == Byte.class || returnType == byte.class) {
			if (resultSet.next()) {
				return resultSet.getByte(1);
			}
			return null;
		} else if (returnType == String.class) {
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
			return null;
		} else if (returnType.isAnnotationPresent(Bean.class)) {
			Object returnObjInstance = null;
			if (resultSet.next()) {
				try {
					returnObjInstance = returnType.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				returnObjInstance = convert2JavaObj(returnType, resultSet);
			}
			return returnObjInstance;
		} /*else if (returnType == List.class) {
			//TODO list,map ,set的解析，目前还不知道怎么取舍。目前先实现LIST，用IResultSetExtracter去实现更合适，但是又要每个人都写一个相应的实现
			List returnObjInstance = new LinkedList<>();
			Type type = actualTypeArguments[0];
			String typeName = type.getTypeName();
			Class<?> javaObjClazz = null;
			try {
				javaObjClazz = Class.forName(typeName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			while (resultSet.next()) {
				if (javaObjClazz.isAnnotationPresent(Bean.class)) {
					returnObjInstance.add(convert2JavaObj(javaObjClazz, resultSet));
				} else if (javaObjClazz == Integer.class || javaObjClazz == int.class) {
					returnObjInstance.add(resultSet.getInt(1));
				} else if (javaObjClazz == Float.class || javaObjClazz == float.class) {
					returnObjInstance.add(resultSet.getFloat(1));
				} else if (javaObjClazz == Long.class || javaObjClazz == long.class) {
					returnObjInstance.add(resultSet.getLong(1));
				} else if (javaObjClazz == Double.class || javaObjClazz == double.class) {
					returnObjInstance.add(resultSet.getDouble(1));
				} else if (javaObjClazz == Short.class || javaObjClazz == short.class) {
					returnObjInstance.add(resultSet.getShort(1));
				} else if (javaObjClazz == Byte.class || javaObjClazz == byte.class) {
					returnObjInstance.add(resultSet.getByte(1));
				} else if (javaObjClazz == String.class) {
					returnObjInstance.add(resultSet.getString(1));
				}
			}
			return returnObjInstance;
		} */else {
			LOGGER.error("查询结果后，根据返回类型找不到相应的处理,methodName = |{}|  ,returnType= {} ", param.getMethodName(), returnType);
			return null;
		}
	}

	/****
	 * 
	 * @param javaObjClazz
	 *            要转换成目标的java对象
	 * @param resultSet
	 *            数据库查询出的一行记录
	 * @return 将数据对象转成java对象
	 * @throws SQLException
	 */
	private Object convert2JavaObj(Class<?> javaObjClazz, ResultSet resultSet) throws SQLException {
		Map<Field, Method> setMethodMap = new HashMap<>();
		Object javaObjInstance = null;
		try {
			javaObjInstance = javaObjClazz.newInstance();

			Field[] declaredFields = javaObjClazz.getDeclaredFields();
			for (Field f : declaredFields) {
				String name = f.getName();
				Method setMethod = javaObjClazz.getMethod(String.format("set%s", StringUtil.toUpperCaseAt0(name)),
						f.getType());
				setMethodMap.put(f, setMethod);
			}

			for (Entry<Field, Method> entry : setMethodMap.entrySet()) {
				Field f = entry.getKey();
				Method m = entry.getValue();
				Class<?> type = f.getType();
				String name = f.getName();
				if (type == Integer.class || type == int.class) {
					m.invoke(javaObjInstance, resultSet.getInt(name));
				} else if (type == Float.class || type == float.class) {
					m.invoke(javaObjInstance, resultSet.getFloat(name));
				} else if (type == Long.class || type == long.class) {
					m.invoke(javaObjInstance, resultSet.getLong(name));
				} else if (type == Double.class || type == double.class) {
					m.invoke(javaObjInstance, resultSet.getDouble(name));
				} else if (type == Short.class || type == short.class) {
					m.invoke(javaObjInstance, resultSet.getShort(name));
				} else if (type == Byte.class || type == byte.class) {
					m.invoke(javaObjInstance, resultSet.getByte(name));
				} else if (type == String.class) {
					m.invoke(javaObjInstance, resultSet.getString(name));
				} else {
					LOGGER.error("没有处理这种解析类型,class = {} ,类型={}", javaObjClazz, type);
				}

			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return javaObjInstance;
	}
}
