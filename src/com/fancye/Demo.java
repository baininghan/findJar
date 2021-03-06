package com.fancye;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <code>FindJar</code> ʹ��ʾ��
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
		
		try {
			fj.iteratorDir(fileSrc, className);
		} catch (FileNotFoundException e) {
			System.out.println("The file path error, please give the correct path to the file!");
		}
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
