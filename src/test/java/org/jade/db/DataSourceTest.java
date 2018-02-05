/**
 * 
 */
package org.jade.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

/**
 * @author Jack Lei
 * @Time 2018年2月5日 下午3:50:46
 * @Email 895896736@qq.com
 */

public class DataSourceTest extends TestCase {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						testQuery();
						Thread.sleep(30L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "#T1").start();
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						testQuery();
						Thread.sleep(30L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "#T2").start();
		
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						testQuery();
						Thread.sleep(30L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "#T3").start();
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
}
