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
}
