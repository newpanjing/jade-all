/**
 * 
 */
package org.jade.core.api;

import java.sql.ResultSet;

/**
 * 抽象查询出数据库结果后的处理
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */
public interface IResultSetExtracter<T> {
	T extract(ResultSet resultSet);
}
