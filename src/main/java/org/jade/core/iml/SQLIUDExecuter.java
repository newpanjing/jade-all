package org.jade.core.iml;

import java.sql.SQLException;

import org.jade.core.api.ISQLExecuter;
import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterContext;

/***
 * <pre>
 * SQL语句增删改执行器
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
public class SQLIUDExecuter implements ISQLExecuter {
	public static final SQLIUDExecuter INSTANCE = new SQLIUDExecuter();

	private SQLIUDExecuter() {
	}

	@Override
	public SQLType getType() {
		return SQLType.INSERT;
	}

	@Override
	public Object execute(SQLExecuterContext param) throws SQLException{

		return param.getStatement().executeUpdate();
	}

}
