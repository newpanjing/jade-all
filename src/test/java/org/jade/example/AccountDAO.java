/**
 * 
 */
package org.jade.example;

import java.util.List;

import org.jade.core.api.DAO;
import org.jade.core.api.SQL;
import org.jade.core.constrant.SQLType;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@DAO
public interface AccountDAO {

  
	
	@SQL(val = "Select * from Account ", type = SQLType.SELECT)
	Account query();
	
	@SQL(val = "Select * from Account ", type = SQLType.SELECT)
	List<Account> queryList();

	@SQL(val="Select * from Account Where id = ?",type = SQLType.SELECT)
	Account queryById(int id);
	
	@SQL(val="Select * from Account Where id =? and name =?",type = SQLType.SELECT)
	Account queryById2(int id, String name);
	
	@SQL(val="Delete From Account where id = ?",type=SQLType.INSERT)
	int deleteById(int id);

	@SQL(val="Update Account set name = ? where id = ?",type = SQLType.UPDATE)
	int updateById(int id,String name);
	
}
