/**
 * 
 */
package org.jade.core.api;

import java.sql.SQLException;

import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterParam;
import org.jade.core.exception.SQLExecuteException;

/**
 * <pre>
 * SQL语句执行器
 * </pre>
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public interface ISQLExecuter {

	SQLType getType();

	Object execute(SQLExecuterParam param) throws SQLException,SQLExecuteException;
}
