/**
 * 
 */
package org.jade.core.domain;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 包装了SQL对象转成JAVA对象的
 * </pre>
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class ReflectContext {
	private Map<String, Method> getMethodMap = new HashMap<>();
	private Map<String, Method> setMethodMap = new HashMap<>();
	
	public void addGetMethod(String filedName,Method m){
		getMethodMap.put(filedName, m);
	}
	
	public void addSetMethod(String filedName,Method m){
		setMethodMap.put(filedName, m);
	}
	 
}
