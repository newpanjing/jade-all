/**
 * 
 */
package org.jade.core.domain;

import org.jade.core.constrant.SQLParamType;

/**
 * 参数对象
 * <ul>
 * <li>只解析等于号后面的，以'{'开始,以'}'结束
 * </ul>
 * 
 * 
 * 
 * 
 * " Select * from Account where id={1} and name = {2} "
 * 
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SQLParamNode {
	private SQLParamType type;

	public SQLParamNode(SQLParamType type) {
		super();
		this.type = type;
	}

	public SQLParamType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "SQLParamNode [type=" + type + "]";
	}
	
}
