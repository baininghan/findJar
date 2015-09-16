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
		String fileSrc = "D:\\Java";
		String className = "String";

		Scanner s = new Scanner(System.in);
		System.out.print("Please enter a file path: ");
		fileSrc = s.next();
		System.out.print("Please enter your class name: ");
		className = s.next();
		System.out.println("Start search: ");

		FindJar fj = new FindJar("jar", "class");
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