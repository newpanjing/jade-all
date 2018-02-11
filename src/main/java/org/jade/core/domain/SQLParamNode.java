/**
 * 
 */
package org.jade.core.domain;

import org.jade.core.constrant.SQLParamType;

/**
 * 
 * <pre>
 * 在SQL注解中的sql模板中的可变参数的包装类，支持的可变参数类型如下:
 * <ul>
 * <li>%s</li>
 * <li>%d</li>
 * </ul>
 * @see {@link SQLParamType}
 * </pre>
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
