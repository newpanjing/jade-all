package org.jade.example;

import java.util.List;

import org.jade.core.JadeBootstrap;
import org.jade.core.JadeDAOService;

public class JadeTest {

	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		//AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);
		// int insert = dao.insert(26, "test");
		// System.out.println("insert "+ insert);
		//
		// int insert2 = dao.insert(27, "test");
		// System.out.println("insert2 "+ insert2);
		//
		// int insert3 = dao.insert(28, "test");
		// System.out.println("insert3 "+ insert3);
		//
		// int insert4 = dao.insert(29, "test");
		// System.out.println("insert4 "+ insert4);
		//
		// int deleteById = dao.deleteById(1);
		// System.out.println("deleteById "+ deleteById);

		// Account query = dao.query();
		// System.out.println("query "+ query);
		//
		// Account queryById = dao.queryById(9);
		// System.out.println("queryById "+ queryById);
		//
		// Account queryById2 = dao.queryById2(1, "test");
		// System.out.println("queryById2 "+ queryById2);
		//
		// int updateById = dao.updateById(1, "test2");
		// System.out.println("updateById "+ updateById);
		//
		// List<Account> queryList = dao.queryList();
		// System.out.println("queryList "+queryList);
		testInsert();
		testQuery();

	}

	private static void testInsert() {
		/***
		 * <pre>
		 * +++++++++++++++
		 * 测试插入
		 * +++++++++++++++
		 * </pre>
		 */
		AccountInsertDAO dao = JadeDAOService.getDao(AccountInsertDAO.class);
		int insertDouble = dao.insertDouble(2001D, "test");
		System.out.println("insertDouble "+insertDouble);
		
		int insertFloat = dao.insertFloat(2002F, "test");
		System.out.println("insertFloat "+insertFloat);
		
		int insertInt = dao.insertInt(2003, "test");
		System.out.println("insertInt "+insertInt);
		
		int insertShort = dao.insertShort((short)23, "test");
		System.out.println("insertShort "+ insertShort);
		
		int insertByte = dao.insertByte((byte)1, "test");
		System.out.println("insertByte "+insertByte);
		
		int insertStr = dao.insertStr("666", "test");
		System.out.println("insertStr "+insertStr);
	}

	private static void testQuery() {
		/***
		 * <pre>
		 * +++++++++++++++
		 * 测试查询
		 * +++++++++++++++
		 * </pre>
		 */
		AccountQueryDAO dao = JadeDAOService.getDao(AccountQueryDAO.class);

		Byte queryCount4Byte = dao.queryCount4Byte();
		System.out.println("queryCount4Byte " + queryCount4Byte);

		float queryCount4Float = dao.queryCount4Float();
		System.out.println("queryCount4Float " + queryCount4Float);

		int queryCount4Int = dao.queryCount4Int();
		System.out.println("queryCount4Int " + queryCount4Int);

		Long queryCount4Long = dao.queryCount4Long();
		System.out.println("queryCount4Long " + queryCount4Long);

		short queryCount4Short = dao.queryCount4Short();
		System.out.println("queryCount4Short " + queryCount4Short);

		String queryCount4String = dao.queryCount4String();
		System.out.println("queryCount4String " + queryCount4String);

		Account query = dao.query();
		System.out.println("query " + query);

		List<Account> queryList = dao.queryList();
		System.out.println(queryList);

		List<Integer> queryList4Int = dao.queryList4Int();
		System.out.println("queryList4Int  " + queryList4Int);

	}
}
