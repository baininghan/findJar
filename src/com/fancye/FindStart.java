package com.fancye;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FindStart {
	public static void main(String[] args) {
		String fileSrc = System.getProperty("user.dir");
		String className = "String";
		String suffix = "jar";
		String targetName = "class";
		
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\n");
		
		// 获取查找目录
		System.out.print("Please enter a directory, default to the current directory:");
		fileSrc = s.next();
		if (fileSrc.equals(".") || null == fileSrc || "".equals(fileSrc) || "\r".equals(fileSrc)) {
			System.getProperty("user.dir");
		} else {
			File file = new File(fileSrc);
			if (file.isDirectory()) {
				// 获取查找压缩包的后缀名，默认jar
				System.out.print("Please enter the compressed package name, the default is jar:");
				suffix = s.next();
				if (suffix.equals(".") || null == suffix || "".equals(suffix) || "\r".equals(suffix)) {
					suffix = "jar";
				}
			}
		}
		
		
		System.out.print("Please enter your class name: ");
		className = s.next();
		// 获取查找文件名
		if (className.equals(".") || null == className || "".equals(className)) {
			System.out.print("Please enter a file path: ");
			className = s.next();
		}
		
		System.out.println(className.lastIndexOf("."));
		if (className.lastIndexOf(".") == -1) {// 如果输入的文件名没有带后缀名
			// 获取查找文件名后缀，默认为class
			System.out.print("Please enter a file name suffix, the default is class:");
			targetName = s.next();
			if (targetName.equals(".") || null == targetName || "".equals(targetName) || "\r".equals(targetName)) {
				targetName = "class";
			}
		}
		
		start(s, fileSrc, className, suffix, targetName);
	}
	
	/**
	 * 
	 * @param s
	 * @param fileSrc 压缩包名称
	 * @param className	查找文件名称
	 * @param suffix	压缩包后缀名
	 * @param targetName	查找文件后缀名
	 */
	public static void start(Scanner s, String fileSrc, String className, String suffix, String targetName){
		System.out.println("Start search...");

		FindJar fj = new FindJar(suffix, targetName);
		try {
			fj.iteratorDir(fileSrc, className);
		} catch (FileNotFoundException e) {
			System.out.println("fatal: Your File [" + fileSrc
					+ "] not found!!!");
		}
		List<Object[]> files = fj.jarList;
		int num = 0;
		StringBuffer sb = new StringBuffer();
		if(files.isEmpty()){System.out.println("Not found!");}
		for(Object[] obj : files){
			File file = (File)obj[0];
			String sbStr = "Your jar name is : <" + file.getName() + ">, from : <" + file.toString() +
					">, It's full package path : <" + obj[1] + ">";
			sb.append(sbStr).append("\r\n");
			System.out.println(sbStr);
			++num;
		}
		System.out.println("Search end,total found: " + num);
		if (num >= 2) {
			System.out
					.println("The number of over ["
							+ num
							+ "] search results , whether stored in a local file?(y/n)");
			String y_n = s.next();
			if (y_n.equalsIgnoreCase("y")) {
				String dir = fileSrc + "\\" + className + ".txt";
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter(new File(dir)));
					bw.write(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Fatal: IOException!!!");
				} finally {
					if (bw != null)
						try {
							bw.close();
						} catch (IOException e) {
							System.out
									.println("Fatal: Writer closed IOException!!!");
						}
				}
				System.out.println("Results writed in: " + dir);
			}
			System.out.println("Done ...");
		}
		s.close();
		System.exit(0);
	}
}