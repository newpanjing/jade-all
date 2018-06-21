package org.jade.core.constrant;

public interface ErrorCode {

	public static final int SUCC = 0;

	/*** 设置PrepareStatement参数错误 */
	public static final int SQL_PARAM_PRE_SET_ERROR = -1001;
	/*** 这种类型的参数还没处理 */
	public static final int SQL_PARAM_PRE_SET_NOT_SERV = -1002;
	/***PreparedStatement执行SQL语句时出现异常*/
	public static final int SQL_PREPAREDSTATEMENT_SQL_EXCEPTION = -1003;
}
