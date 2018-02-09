/**
 * 
 */
package org.jade.core.constrant;

/**
 * 
 * SQL参数类型
 * @author Jack Lei
 * @Time 2018年2月9日 下午3:37:32
 * @Email 895896736@qq.com
 */

public enum SQLParamType {
	
	INT(0,"%d",Integer.class),
	
	STRING(1,"%s",String.class),
	
	LONG(2,"%l",Long.class);
	
	private int type;
	
	private String key;
	
	private Class<?> clazz;

	/**
	 * @param type
	 * @param key
	 * @param clazz
	 */
	private SQLParamType(int type, String key, Class<?> clazz) {
		this.type = type;
		this.key = key;
		this.clazz = clazz;
	}

	public int getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public Class<?> getClazz() {
		return clazz;
	}
	
	public static SQLParamType getSQLParamType(String key){
		for(SQLParamType spt: values()){
			if(spt.getKey().equals(key)){
				return spt;
			}
		}
		return null;
	}
	
	
}
