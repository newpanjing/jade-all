package org.jade.test;

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
//		Account queryById2 = dao.queryById2(1, "2");
//		System.out.println("queryById2 "+ queryById2);
		//int deleteById = dao.deleteById(1);
		//System.out.println("deleteById "+deleteById);
		// int updateById = dao.updateById(2, "leixuan");
		// System.out.println("updateById "+ updateById);
		int insert = dao.insert(11, "inserter");
		System.out.println("insert "+insert);
		
	}
}
