package org.jade.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Jade启动入口
 *	@author Jack Lei
 * @Email 895896736@qq.com
 */
public class JadeBootstrap {
	private static final Logger LOGGER = LoggerFactory.getLogger(JadeBootstrap.class);
	
	public static void start(String daoPackage){
		long startMillis = System.currentTimeMillis();
		LOGGER.info("Jade start  ");
		JadeDAOService.registerDao(daoPackage);
		LOGGER.info("Jade start  success ,cost {} ms",System.currentTimeMillis()- startMillis);
	}
}
