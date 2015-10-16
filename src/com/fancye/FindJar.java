package com.fancye;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * 此类用于遍历一个文件目录下可能存在的所有压缩包中是否存在指定的文件
 * @author Fancye
 * @version v1.0
 */
public class FindJar {

	/** 遍历目标压缩包文件的后缀名，默认查找 .jar */
	private String suffix = "jar";
	/** 查找目标文件的后缀名，默认查找 .class */
	private String targetName = "class";
	/** 存放查找到的文件 */
	public List<Object[]> jarList = new ArrayList<Object[]>();
	
	public FindJar(){}
	
	/**
	 * 重新构造查找策略
	 * @param sufName 遍历目标压缩包文件的后缀名
	 * @param targetName 查找条件的后缀名
	 */
	public FindJar(String suffix, String targetName){
		if(!"".equals(suffix) && suffix != null)
			this.suffix = suffix;
		if(!"".equals(targetName) && targetName != null)
			this.targetName = targetName;
	}
	
	public static void init(String className) {
		if (className.lastIndexOf(".") > -1) {
		}
	}
	
	/**
	 * 文件或者文件目录的检查
	 * @param fileSrc
	 * @param className
	 * @throws FileNotFoundException
	 */
	public void iteratorDir(String fileSrc, String className) throws FileNotFoundException {
		File fileDir = new File(fileSrc);
		checkFileExists(fileDir);
		
		exeFile(fileDir, className);
	}
	
	/**
	 * 遍历文件或者文件目录
	 * @param fileSrc 一个完整的文件目录
	 * @param className 指定要查找的Class名称
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	private void exeFile(File fileName, String className) {
		exeFile(fileName, className, false);
	}
	
	/**
	 * 遍历文件或者文件目录
	 * @param fileSrc 一个完整的文件目录
	 * @param className 指定要查找的Class名称
	 * @param hasSuffix 是否指定了要遍历的文件压缩包的后缀名
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	private void exeFile(File fileName, String className, boolean hasSuffix) {
		if (fileName.isDirectory()) {
			File[] files = fileName.listFiles();
			for (File file : files) {
				exeFile(file, className);
			}
		} else if (fileName.isFile()
				&& (hasSuffix == true?fileName.getName().endsWith("." + suffix):false)) {
			stdoutFile(fileName, className);
		}
	}
	
	/**
	 * 检查文件目录是否存在，如果不存在抛出异常
	 * @param fileDir 完整的文件目录名称
	 * @throws FileNotFoundException 所给的文件目录没有找到
	 */
	private void checkFileExists(File fileDir) throws FileNotFoundException {
		if(!fileDir.exists()){
			throw new FileNotFoundException();
		}
	}
	/**
	 * 处理具体的压缩包
	 * @param file 正在遍历的压缩文件名称
	 * @param className 指定要查找的文件名称
	 */
	private void stdoutFile(File file, String className) {
		try {
			ZipFile zf = new ZipFile(file);
			Enumeration<? extends ZipEntry> e = zf.entries();
			while(e.hasMoreElements()){
				ZipEntry ze = (ZipEntry)e.nextElement();
				String zeName = ze.getName();
				if(!zeName.endsWith("." + targetName))continue;
				String zeClassName = zeName.substring(zeName.lastIndexOf("/") + 1, zeName.lastIndexOf("." + targetName));
				if(zeClassName.equalsIgnoreCase(className)){
					Object[] obj = new Object[]{file, zeName};
					jarList.add(obj);
				}
			}
			zf.close();
		} catch (ZipException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
