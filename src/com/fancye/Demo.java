package com.fancye;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <code>FindJar</code> Ê¹ÓÃÊ¾Àý
 * @author Fancye
 * @version v.10
 */
public class Demo {

	public static void main(String[] args) throws FileNotFoundException {
		String fileSrc = "D:\\Java";
		String className = "String";
		FindJar fj = new FindJar("jar", "class");
		fj.iteratorDir(fileSrc, className);
		List<File> files = fj.jarList;
		for(File file : files){
			System.out.println("Your jar name is : " + file.getName() + ", from : " + file.toString());
		}
	}

}
