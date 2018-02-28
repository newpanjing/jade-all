package org.jade.test;

import org.jade.core.JadeBootstrap;
import org.jade.core.JadeDAOService;

public class JadeTest {
	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);
		Account test = dao.test();
		System.out.println(test);
	}
}
