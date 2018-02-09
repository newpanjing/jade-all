/**
 * 
 */
package org.jade.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class DataSourceManager {

	private static BasicDataSource dataSource;

	private static final Properties dbProperties;

	private static final DataSourceManager INSTANCE = new DataSourceManager();

	private DataSourceManager() {

	}

	static {
		dbProperties = new Properties();

		try {
			dbProperties.load(DataSourceManager.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			dataSource = BasicDataSourceFactory.createDataSource(dbProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DataSourceManager getInstance() {
		return INSTANCE;
	}

	public Connection getConnection() throws SQLException {
		if (dataSource == null) {
			return null;
		}
		return dataSource.getConnection();
	}
}
