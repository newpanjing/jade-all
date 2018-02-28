package org.jade.test;

import java.util.List;

import org.jade.core.JadeBootstrap;
import org.jade.core.JadeDAOService;

public class JadeTest {
	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);
//		Account test = dao.query();
//		System.out.println("query "+ test);
//		List<Account> queryList = dao.queryList();
//		System.out.println("query List "+ queryList);
//		Account queryById = dao.queryById(1);
//		System.out.println("queryById "+ queryById);
		dao.queryById2(1, "2");
	}
}
