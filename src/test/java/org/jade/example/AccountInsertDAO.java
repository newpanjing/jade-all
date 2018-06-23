/**
 * 
 */
package org.jade.example;

import org.jade.core.api.DAO;
import org.jade.core.api.SQL;
import org.jade.core.constrant.SQLType;

/**
 * 查询测试
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@DAO
public interface AccountInsertDAO {

	@SQL(val="Insert into Account()Values(?,?)",type = SQLType.INSERT)
	int insertInt(int id, String name);
	
	@SQL(val="Insert into Account()Values(?,?)",type = SQLType.INSERT)
	int insertFloat(float id, String name);
	
	@SQL(val="Insert into Account()Values(?,?)",type = SQLType.INSERT)
	int insertDouble(double id, String name);

	@SQL(val="Insert into Account()Values(?,?)",type = SQLType.INSERT)
	int insertShort(short id, String name);
	
	@SQL(val="Insert into Account()Values(?,?)",type = SQLType.INSERT)
	int insertByte(byte id, String name);
	
	@SQL(val="Insert into Account()Values(?,?)",type = SQLType.INSERT)
	int insertStr(String id, String name);
}
