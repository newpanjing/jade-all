/**
 * 
 */
package org.jade.core;

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
	Account test();
	
	@SQL(val = "Select * from Account ", type = SQLType.SELECT)
	List<Account> testList();

}
