/**
 * 
 */
package org.jade.core.constrant;

/**
 * @author Jack Lei
 * @Time 2018年2月5日 下午7:34:55
 * @Email 895896736@qq.com
 */

public enum SQLType {
	
	SELECT(1,"SELECT"),
	INSERT(2,"INSERT"),
	UPDATE(3,"UPDATE"),
	DELETE(4,"DELETE");
	
	private int type;
	
	private String desc;

	private SQLType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
}
