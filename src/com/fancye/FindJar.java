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
 * �������ڱ���һ���ļ�Ŀ¼�¿��ܴ��ڵ�����ѹ����(.jar)���Ƿ����ָ����Class�ļ�
 * @author Fancye
 * @version v1.0
 */
public class FindJar {

	/** ����Ŀ��ѹ�����ļ��ĺ�׺����Ĭ�ϲ��� .jar */
	private String sufName = "jar";
	/** ����Ŀ���ļ��ĺ�׺����Ĭ�ϲ��� .class */
	private String targetName = "class";
	/** ��Ų��ҵ����ļ� */
	public List<Object[]> jarList = new ArrayList<Object[]>();
	
	public FindJar(){}
	
	/**
	 * ���¹�����Ҳ���
	 * @param sufName ����Ŀ��ѹ�����ļ��ĺ�׺��
	 * @param targetName ���������ĺ�׺��
	 */
	public FindJar(String sufName, String targetName){
		if(!"".equals(sufName) && sufName != null)
			this.sufName = sufName;
		if(!"".equals(targetName) && targetName != null)
			this.targetName = targetName;
	}
	
	/**
	 * �ļ������ļ�Ŀ¼�ļ��
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
	 * �����ļ������ļ�Ŀ¼
	 * @param fileSrc һ���������ļ�Ŀ¼
	 * @param className ָ��Ҫ���ҵ�Class����
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	private void exeFile(File fileName, String className) {
		if (fileName.isDirectory()) {
			File[] files = fileName.listFiles();
			for (File file : files) {
				exeFile(file, className);
			}
		} else if (fileName.isFile()
				&& fileName.getName().contains("." + sufName)) {
			stdoutFile(fileName, className);
		}
	}
	
	/**
	 * ����ļ�Ŀ¼�Ƿ���ڣ�����������׳��쳣
	 * @param fileDir �������ļ�Ŀ¼����
	 * @throws FileNotFoundException �������ļ�Ŀ¼û���ҵ�
	 */
	private void checkFileExists(File fileDir) throws FileNotFoundException {
		if(!fileDir.exists()){
			throw new FileNotFoundException();
		}
	}
	/**
	 * ��������Jar��
	 * @param file ���ڱ�����Jar������
	 * @param className ָ��Ҫ���ҵ�Class����
	 */
	private void stdoutFile(File file, String className) {
		try {
			ZipFile zf = new ZipFile(file);
			Enumeration<? extends ZipEntry> e = zf.entries();
			while(e.hasMoreElements()){
				ZipEntry ze = (ZipEntry)e.nextElement();
				String zeName = ze.getName();
				if(!zeName.contains("." + targetName))continue;
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
