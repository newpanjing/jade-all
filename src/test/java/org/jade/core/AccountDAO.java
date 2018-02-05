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
 * @Time 2018年2月5日 下午8:06:42
 * @Email 895896736@qq.com
 */
@DAO
public interface AccountDAO {

	@SQL(sql = "Select * from Account", type = SQLType.SELECT)
	List<Account> test();

}
