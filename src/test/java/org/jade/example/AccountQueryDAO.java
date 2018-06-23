/**
 * 
 */
package org.jade.example;

import java.util.List;

import org.jade.core.api.DAO;
import org.jade.core.api.Extracter;
import org.jade.core.api.SQL;
import org.jade.core.constrant.SQLType;
import org.jade.example.iml.ExtractAccountList;

/**
 * 查询测试
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
@DAO
public interface AccountQueryDAO {

	@SQL(val = "Select count(*) from Account ", type = SQLType.SELECT)
	int queryCount4Int();

	@SQL(val = "Select count(*) from Account ", type = SQLType.SELECT)
	float queryCount4Float();

	@SQL(val = "Select count(*) from Account ", type = SQLType.SELECT)
	short queryCount4Short();

	@SQL(val = "Select count(*) from Account ", type = SQLType.SELECT)
	Long queryCount4Long();

	@SQL(val = "Select count(*) from Account ", type = SQLType.SELECT)
	Byte queryCount4Byte();

	@SQL(val = "Select count(*) from Account ", type = SQLType.SELECT)
	String queryCount4String();

	@SQL(val = "Select * from Account", type = SQLType.SELECT)
	Account query();

	@SQL(val = "Select * from Account", type = SQLType.SELECT)
	List<Account> queryList();

	@Extracter(extracter = ExtractAccountList.class)
	@SQL(val = "Select * from Account", type = SQLType.SELECT)
	List<Integer> queryList4Int();

}
