## FindJar

��ǩ���ո�ָ����� Java
##### ԭ�����ӣ�[FindJar](https://github.com/baininghan/findJar)
---

�������ڱ���һ���ļ�Ŀ¼�¿��ܴ��ڵ�����ѹ����(.jar)���Ƿ����ָ����Class�ļ���
#### ������`D:\Java`Ŀ¼�µ�`String.class`���ڵ�`jar`����
```java
String fileSrc = "D:\\Java";
String className = "String";
FindJar fj = new FindJar();
fj.iteratorDir(fileSrc, className);
List<File> files = fj.jarList;
for(File file : files){
	System.out.println("Your jar name is : " + file.getName() 
	                + ", from : " + file.toString());
}
```
#### ����ָ����׺����ѹ��������Ҫ���ҵ�ָ����׺�����ļ������������ʾ��������`D:\Java`Ŀ¼�º�׺����`.zip`��ѹ�����д��ڵ�`request.htmll`�ļ���
```
String fileSrc = "D:\\Java";
String className = "request";
FindJar fj = new FindJar("zip", "html");
fj.iteratorDir(fileSrc, className);
List<File> files = fj.jarList;
for(File file : files){
	System.out.println("Your jar name is : " + file.getName() 
	                + ", from : " + file.toString());
}
```