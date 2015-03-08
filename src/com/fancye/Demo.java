package com.fancye;

import java.io.File;
import java.util.List;

/**
 * <code>FindJar</code> Ê¹ÓÃÊ¾Àý
 * @author Fancye
 * @version v0.2
 */
public class Demo {

	public static void main(String[] args) {
		String fileSrc = "D:\\Java";
		String className = "String";
		FindJar fj = new FindJar("jar", "class");
		
//		String fileSrc = "D:\\Java";
//		String className = "register";
//		FindJar fj = new FindJar("zip", "html");
		
		fj.iteratorDir(fileSrc, className);
		List<Object[]> files = fj.jarList;
		if(files.isEmpty()){System.out.println("Not found!");}
		for(Object[] obj : files){
			File file = (File)obj[0];
			System.out.println("Your jar name is : <" + file.getName() + ">, from : <" + file.toString() +
					">, It's full package path : <" + obj[1] + ">");
			System.out.println("------");
		}
	}

}
