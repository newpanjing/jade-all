/**
 * 
 */
package org.jade.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jade.core.AccountDAO;

import junit.framework.TestCase;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class DataSourceTest extends TestCase {
	private static AccountDAO dao;

	public static void main(String[] args) {
		
		 dao.test();
	}

	public static void testQuery() {
		System.out.println("####" + Thread.currentThread().getName() + " start ####");
		Connection connection = null;
		try {
			connection = DataSourceManager.getInstance().getConnection();
			System.out.println("#### con = " + connection + "   ####");
			Statement stateMent = connection.createStatement();
			ResultSet result = stateMent.executeQuery("select * from Account");
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				System.out.println("id :" + id + " name :" + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
					System.out.println("####" + Thread.currentThread().getName() + " release ####");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("####" + Thread.currentThread().getName() + " end ####");
	}
	
	public static void testUpdate() {
		System.out.println("####" + Thread.currentThread().getName() + " start ####");
		Connection connection = null;
		try {
			connection = DataSourceManager.getInstance().getConnection();
			System.out.println("#### con = " + connection + "   ####");
			Statement stateMent = connection.createStatement();
			int executeUpdate = stateMent.executeUpdate("update Account Set name = ");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
					System.out.println("####" + Thread.currentThread().getName() + " release ####");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("####" + Thread.currentThread().getName() + " end ####");
	}
}
