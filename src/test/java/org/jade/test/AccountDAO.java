/**
 * 
 */
package org.jade.test;

import java.util.List;

import org.jade.core.api.DAO;
import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;
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

	@SQL(val="Select * from Account Where id = ID",type = SQLType.SELECT)
	Account queryById(@SQLParam("ID")int id);
	
	@SQL(val="Select * from Account Where id =ID and name =NAME",type = SQLType.SELECT)
	Account queryById2(@SQLParam("ID")int id,@SQLParam("NAME")String name);
	
	@SQL(val="Delete From Account where id = ID",type=SQLType.INSERT)
	int deleteById(@SQLParam("ID")int id);
	
	@SQL(val="Update Account set name = 'NAME' where id = ID",type = SQLType.UPDATE)
	int updateById(@SQLParam("ID")int id,@SQLParam("NAME")String name);
	
	@SQL(val="Insert into Account()Values(ID,'NAME')",type = SQLType.INSERT)
	int insert(@SQLParam("ID")int id,@SQLParam("NAME")String name);
}
