package org.jade.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * @author Jack Lei
 * @Time 2018年1月30日 下午9:10:55
 * @Email 895896736@qq.com
 */
public class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger("classutil");

	public static List<Class<?>> findClassByPackagName(String packageName) {
		List<Class<?>> classesList = new ArrayList<>();
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace(".", "/");
		List<String> packageDirList = new LinkedList<>();
		List<String> classNameList = new LinkedList<>();
		try {
			Enumeration<URL> resources = contextClassLoader.getResources(path);
			while (resources.hasMoreElements()) {
				URL nextElement = resources.nextElement();
				String file = nextElement.getFile();
				packageDirList.add(file);
			}
			for (String filePath : packageDirList) {
				List<String> findclass = findClassByDir(packageName, filePath);
				if (!findclass.isEmpty()) {
					classNameList.addAll(findclass);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return classesList;
	}

	/**
	 * @param packageName
	 * @param classNameList
	 * @param filePath
	 */
	private static List<String> findClassByDir(String packageName, String filePath) {
		List<String> classNameList = new LinkedList<>();
		File fileDir = new File(filePath);
		File[] listFiles = fileDir.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				findClassByDir(packageName, file.getAbsolutePath());
			} else {
				String name = file.getName();
				if (name.endsWith(".class")) {
					String className = packageName + "." + name.substring(0, name.length() - 6);
					classNameList.add(className);
					LOGGER.info("find class{} ", className);
				} else {

				}
			}
		}
		return classNameList;
	}

	public static void main(String[] args) {
		findClassByPackagName("org.jade");
	}
}
