/**
 * 
 */
package org.jade.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jade.core.api.IResultSetExtracter;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
public class AccountResultSetExtracter implements IResultSetExtracter<List<Account>> {

	@Override
	public List<Account> extract(ResultSet resultSet) {
		 try {
			resultSet.getString("1");
			resultSet.getString("1");
			resultSet.getString("1");
			resultSet.getString("1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return null;
	}

}
