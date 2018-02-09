/**
 * 
 */
package org.jade.core;

import java.lang.reflect.Proxy;

import org.jade.core.invokehandler.JadeInvokeHandler;

import junit.framework.TestCase;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class InvokehanderTest extends  TestCase{
	
	public static void main(String args[]){
		JadeInvokeHandler jadeInvokeHandler = new JadeInvokeHandler();
		AccountDAO newProxyInstance = (AccountDAO) Proxy.newProxyInstance(InvokehanderTest.class.getClassLoader(), new Class[]{AccountDAO.class}, jadeInvokeHandler);
		newProxyInstance.test();
		newProxyInstance.test("jack");
	}
}
