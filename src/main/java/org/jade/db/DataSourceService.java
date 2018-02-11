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
import org.jade.core.iml.SQLQueryExecuter;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class DataSourceService {

	public static Object execute(SQLType type, String sql, Class<?> returnType, Type[] actualTypeArguments) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DataSourceManager.getInstance().getConnection();
			statement = connection.createStatement();
			switch (type) {
			case DELETE:
				break;
			case INSERT:
				break;
			case SELECT:
				return SQLQueryExecuter.INSTANCE.execute(new SQLExecuterParam(statement, sql, returnType, actualTypeArguments));
			case UPDATE:
				break;
			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLExecuteException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
