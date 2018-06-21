package org.jade.example;

import java.util.List;

import org.jade.core.JadeBootstrap;
import org.jade.core.JadeDAOService;

public class JadeTest {
	
	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);

		int insert = dao.insert(6, "test");
		System.out.println("insert "+ insert);

		int insert2 = dao.insert(7, "test");
		System.out.println("insert2 "+ insert2);

		int insert3 = dao.insert(8, "test");
		System.out.println("insert3 "+ insert3);
		
		int insert4 = dao.insert(9, "test");
		System.out.println("insert4 "+ insert4);
		
		int deleteById = dao.deleteById(1);
		System.out.println("deleteById "+ deleteById); 
		 
		Account query = dao.query();
		System.out.println("query "+ query);
		
		Account queryById = dao.queryById(9);
		System.out.println("queryById "+ queryById);
		
		Account queryById2 = dao.queryById2(1, "test");
		System.out.println("queryById2 "+ queryById2);
		
		 int updateById = dao.updateById(1, "test2");
		 System.out.println("updateById "+ updateById);

		 List<Account> queryList = dao.queryList();
		 System.out.println("queryList "+queryList);
	}
}
