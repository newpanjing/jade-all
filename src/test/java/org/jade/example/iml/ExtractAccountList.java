/**
 * 
 */
package org.jade.example.iml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.jade.core.api.IResultSetExtracter;
import org.jade.example.Account;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
public class ExtractAccountList implements IResultSetExtracter<List<Account>> {

	@Override
	public List<Account> extract(ResultSet resultSet) {
		List<Account> list = new LinkedList<>();
		try {
			while(resultSet.next()){
				Account a = new Account();
				a.setId(resultSet.getInt("id"));
				a.setName(resultSet.getString("name"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
