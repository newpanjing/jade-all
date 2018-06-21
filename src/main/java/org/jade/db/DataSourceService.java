/**
 * 
 */
package org.jade.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jade.core.constrant.ErrorCode;
import org.jade.core.constrant.SQLType;
import org.jade.core.domain.SQLExecuterContext;
import org.jade.core.domain.SQLParamContext;
import org.jade.core.iml.SQLIUDExecuter;
import org.jade.core.iml.SQLQueryExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class DataSourceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceService.class);

	public static Object execute(SQLParamContext context) {
		Object[] methodParams = context.getMethodParams();
		Connection con = null;
		PreparedStatement prepareStatement = null;
		try {
			con = DataSourceManager.getInstance().getConnection();

			String sql = context.getSqlAnnotation().val();
			LOGGER.debug("预编译的sql = {},param={}", sql,context.methodParamToString());
			prepareStatement = con.prepareStatement(sql);
			int checkResult = trySetSQLParam(methodParams, prepareStatement);
			if (checkResult != ErrorCode.SUCC) {
				LOGGER.error("执行sql出现错误 errorcode = {} sql = {} ,methodParam={}", checkResult, sql,
						context.methodParamToString());
				return checkResult;
			}
			SQLType type = context.getSqlAnnotation().type();

			SQLExecuterContext param = new SQLExecuterContext(prepareStatement, sql,
					context.getMethod().getReturnType(), context.getActualTypeArguments());
			switch (type) {
			case DELETE:
				return SQLIUDExecuter.INSTANCE.execute(param);
			case INSERT:
				return SQLIUDExecuter.INSTANCE.execute(param);
			case UPDATE:
				return SQLIUDExecuter.INSTANCE.execute(param);
			case SELECT:
				return SQLQueryExecuter.INSTANCE.execute(param);
			default:
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ErrorCode.SQL_PREPAREDSTATEMENT_SQL_EXCEPTION;
		}  finally {
			try {
				if (prepareStatement != null) {
					prepareStatement.close();
					prepareStatement = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return ErrorCode.SQL_PREPAREDSTATEMENT_SQL_EXCEPTION;
			}
		}
		return ErrorCode.SQL_PREPAREDSTATEMENT_SQL_EXCEPTION;
	}

	private static int trySetSQLParam(Object[] methodParams, PreparedStatement prepareStatement) {
		if (methodParams != null) {
			for (int index = 0; index < methodParams.length; index++) {
				Object paramObj = methodParams[index];
				Class<? extends Object> paramClass = paramObj.getClass();
				try {
					if (paramClass == int.class || paramClass == Integer.class) {
						prepareStatement.setInt(index + 1, Integer.valueOf(paramObj.toString()));
					} else if (paramClass == String.class) {
						prepareStatement.setString(index + 1, paramObj.toString());
					} else if (paramClass == Float.class) {
						prepareStatement.setFloat(index + 1, Float.valueOf(paramObj.toString()));
					} else if (paramClass == Long.class) {
						prepareStatement.setLong(index + 1, Long.valueOf(paramObj.toString()));
					} else {
						return ErrorCode.SQL_PARAM_PRE_SET_NOT_SERV;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return ErrorCode.SQL_PARAM_PRE_SET_ERROR;
				} catch (SQLException e) {
					e.printStackTrace();
					return ErrorCode.SQL_PARAM_PRE_SET_ERROR;
				}

			}
		}
		return ErrorCode.SUCC;
	}
}
