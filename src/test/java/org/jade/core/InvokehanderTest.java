/**
 * 
 */
package org.jade.core;

import java.lang.reflect.Proxy;
import java.util.List;

import org.jade.core.invokehandler.JadeInvokeHandler;

import junit.framework.TestCase;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class InvokehanderTest extends TestCase {

	public static void main(String args[]) {
		JadeInvokeHandler jadeInvokeHandler = new JadeInvokeHandler();
		AccountDAO newProxyInstance = (AccountDAO) Proxy.newProxyInstance(InvokehanderTest.class.getClassLoader(),
				new Class[] { AccountDAO.class }, jadeInvokeHandler);
		Account test = newProxyInstance.test();
		List<Account> testList = newProxyInstance.testList();
		System.out.println(test);
		System.out.println(testList);
	}
}
