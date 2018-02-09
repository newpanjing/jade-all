/**
 * 
 */
package org.jade.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class DataSourceService {

	public static void query(String sql,Class<?> clazz) {
		Connection connection = null;
		try {
			connection = DataSourceManager.getInstance().getConnection();
			Statement createStatement = connection.createStatement();
			ResultSet executeQuery = createStatement.executeQuery(sql);
			while(executeQuery.next()){
				int id = executeQuery.getInt("id");
				String name = executeQuery.getString("name");
				System.out.println(" id "+ id +" name "+name );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
