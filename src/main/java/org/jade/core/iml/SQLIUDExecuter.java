package org.jade.core.iml;

import java.sql.SQLException;
import java.sql.Statement;

import org.jade.core.api.ISQLExecuter;
import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterParam;
import org.jade.core.exception.SQLExecuteException;

/***
 * <pre>SQL语句增删改执行器</pre>
 *	@author Jack Lei
 * @Email 895896736@qq.com
 */
public class SQLIUDExecuter implements ISQLExecuter {
	public static final SQLIUDExecuter INSTANCE = new SQLIUDExecuter();
	
	private SQLIUDExecuter(){}
	
	@Override
	public SQLType getType() {
		return SQLType.INSERT;
	}

	@Override
	public Object execute(SQLExecuterParam param) throws SQLException, SQLExecuteException {
		String sql = param.getSql();
		Statement statement = param.getStatement();
		return statement.executeUpdate(sql);
	}

}
