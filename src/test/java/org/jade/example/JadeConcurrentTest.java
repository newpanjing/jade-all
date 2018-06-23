package org.jade.example;

import java.util.concurrent.atomic.AtomicInteger;

import org.jade.core.JadeBootstrap;
import org.jade.core.JadeDAOService;

public class JadeConcurrentTest {

	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);
		try {
			Thread.sleep(3*1000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					queryById2(dao);
					try {
						Thread.sleep(5L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"T#1").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					updateById(dao);
					try {
						Thread.sleep(5L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"T#2").start();
		
		new Thread(new Runnable() {
			AtomicInteger i = new AtomicInteger(10000);
			@Override
			public void run() {
				while (true) {
					insert(dao,i.getAndIncrement());
					try {
						Thread.sleep(5L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"T#3").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					queryList(dao);
					try {
						Thread.sleep(5L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"T#4").start();
	}

	private static void insert(AccountDAO dao,int id) {
		 // dao.insert(id, "inserter");
	}

	private static void updateById(AccountDAO dao) {
		try{
		  dao.updateById(2, "leixuan");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private static void deleteById(AccountDAO dao) {
		int deleteById = dao.deleteById(1);
		System.out.println("deleteById " + deleteById);
	}

	private static void queryById2(AccountDAO dao) {
		Account queryById2 = dao.queryById2(6, "2");
	}

	private static void queryListByid(AccountDAO dao) {
		Account queryById = dao.queryById(1);
		System.out.println("queryById " + queryById);
	}

	private static void queryList(AccountDAO dao) {
		 dao.queryList();
	}
}
