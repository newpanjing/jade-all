/**
 * 
 */
package org.jade.core.iml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jade.core.api.ISQLExecuter;
import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterParam;
import org.jade.core.exception.SQLExecuteException;
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
	public Object execute(SQLExecuterParam param) throws SQLException, SQLExecuteException {
		String sql = param.getSql();
		Statement statement = param.getStatement();
		Class<?> returnType = param.getReturnType();
		Type[] actualTypeArguments = param.getActualTypeArguments();
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (Exception ex) {
			LOGGER.error("SQLQueryExecuter 执行executeQuery异常，sql = {}", sql);
			throw ex;
		}

		if (returnType == List.class) {
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
				returnObjInstance.add(convert2JavaObj(javaObjClazz, resultSet));
			}
			return returnObjInstance;
		} else if (returnType == Map.class) {
			LOGGER.error("暂时不支持Map的转换");
			return null;
		} else if (returnType == Set.class) {
			LOGGER.error("暂时不支持Set的转换");
			return null;
		} else {
			Object returnObjInstance = null;
			if (resultSet.next()) {
				do {
					try {
						returnObjInstance = returnType.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
					returnObjInstance = convert2JavaObj(returnType, resultSet);
				} while (resultSet.next());
			}
			return returnObjInstance;
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
				if (type == Integer.class || type == int.class) {
					int val = resultSet.getInt(f.getName());
					m.invoke(javaObjInstance, val);
				} else if (type == String.class) {
					String val = resultSet.getString(f.getName());
					m.invoke(javaObjInstance, val);
				} else {
					LOGGER.error("没有处理这种解析类型,类型={}", type);
				}
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return javaObjInstance;
	}
}
