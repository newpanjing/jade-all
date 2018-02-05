/**
 * 
 */
package org.jade.core;

/**
 * @author Jack Lei
 * @Time 2018年2月5日 下午8:12:44
 * @Email 895896736@qq.com
 */

public class Account {
	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}
}
