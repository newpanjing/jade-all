package org.jade.example;

import org.jade.core.JadeBootstrap;
import org.jade.core.JadeDAOService;

public class JadeTest {
	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);
 		dao.deleteById(1);
 		dao.insert(1, "test");
 		dao.query();
 		dao.queryById(1);
 		dao.queryById2(1, "test");
 		dao.updateById(1, "test2");
		
	}
}
