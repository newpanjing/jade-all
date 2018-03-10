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
 * @Email 895896736@qq.com
 */
public class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger("ClassUtil");

	/***
	 * 根据包名查询class
	 * @param packageName
	 * @return
	 */
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
				 findClassByDir(packageName, filePath,classNameList);
			}
			for(String className: classNameList){
				Class<?> clazz = Class.forName(className);
				classesList.add(clazz);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classesList;
	}

	/***
	 * 根据文件目录查询class
	 * @param packageName
	 * @param filePath
	 * @param classNameList
	 */
	private static void findClassByDir(String packageName, String filePath,List<String> classNameList) {
		File fileDir = new File(filePath);
		File[] listFiles = fileDir.listFiles();
		if(listFiles==null){
			return;
		}
		for (File file : listFiles) {
			if (file.isDirectory()) {
				findClassByDir(packageName+"."+file.getName(), file.getAbsolutePath(),classNameList);
			} else {
				String name = file.getName();
				if (name.endsWith(".class")) {
					String className = packageName + "." + name.substring(0, name.length() - 6);
					classNameList.add(className);
					LOGGER.info("find class {} ", className);
				} else {

				}
			}
		}
	}

	public static void main(String[] args) {
		findClassByPackagName("org.jade");
	}
}
