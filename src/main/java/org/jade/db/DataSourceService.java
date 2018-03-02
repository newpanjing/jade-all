/**
 * 
 */
package org.jade.db;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterParam;
import org.jade.core.exception.SQLExecuteException;
import org.jade.core.iml.SQLIUDExecuter;
import org.jade.core.iml.SQLQueryExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class DataSourceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceService.class);

	public static Object execute(SQLType type, String sql, Class<?> returnType, Type[] actualTypeArguments) throws SQLException,SQLExecuteException {
		Connection connection = null;
		Statement statement = null;
		LOGGER.debug("Execute Sql {}",sql);
		try {
			connection = DataSourceManager.getInstance().getConnection();
			statement = connection.createStatement();
			SQLExecuterParam param = new SQLExecuterParam(statement, sql, returnType, actualTypeArguments);
			switch (type) {
			case DELETE:
				return SQLIUDExecuter.INSTANCE.execute(param);
			case INSERT:
				return SQLIUDExecuter.INSTANCE.execute(param);
			case UPDATE:
				return SQLIUDExecuter.INSTANCE.execute(param);
			case SELECT:
				return SQLQueryExecuter.INSTANCE.execute(param);
			default:
				break;
			}

		} catch (SQLException e) {
			throw e;
		} catch (SQLExecuteException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return null;
	}
}
