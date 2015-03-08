## FindJar

标签：Java
原文链接：[FindJar](https://github.com/baininghan/findJar)
---

此类用于遍历一个文件目录下可能存在的所有压缩包(.jar)中是否存在指定的Class文件。
#### 查找在`D:\Java`目录下的`String.class`所在的`jar`包。
```java
String fileSrc = "D:\\Java";
String className = "String";
FindJar fj = new FindJar();
fj.iteratorDir(fileSrc, className);
List<Object[]> files = fj.jarList;
if(files.isEmpty()){System.out.println("Not found!");}
for(Object[] obj : files){
	File file = (File)obj[0];
	System.out.println("Your jar name is : <" + file.getName() + ">, from : <" + file.toString() +
			">, It's full package path : <" + obj[1] + ">");
	System.out.println("------");
}
```
#### 查找指定后缀名的压缩包中想要查找的指定后缀名的文件。例如下面的示例，查找`D:\Java`目录下后缀名是`.zip`的压缩包中存在的`request.html`文件。
```java
String fileSrc = "D:\\Java";
String className = "request";
FindJar fj = new FindJar("zip", "html");
fj.iteratorDir(fileSrc, className);
List<Object[]> files = fj.jarList;
if(files.isEmpty()){System.out.println("Not found!");}
for(Object[] obj : files){
	File file = (File)obj[0];
	System.out.println("Your jar name is : <" + file.getName() + ">, from : <" + file.toString() +
			">, It's full package path : <" + obj[1] + ">");
	System.out.println("------");
}
```

#### 在命令行执行
你也可以在CMD命令窗口通过
```java
java -jar findJar.jar
```
来执行。

输出结果：
```bash
E:\item\Git\github\findJar>java -jar findJar.jar
Please enter a file path: d:\java
Please enter your class name: String
Start search:
Your jar name is : <rt.jar>, from : <d:\java\jdk1.7.0_04\jre\lib\rt.jar>, It's f
ull package path : <com/sun/org/apache/xpath/internal/operations/String.class>
Your jar name is : <rt.jar>, from : <d:\java\jdk1.7.0_04\jre\lib\rt.jar>, It's f
ull package path : <java/lang/String.class>
Search end,total found: 2
The number of over [2] search results , whether stored in a local file?(y/n)
n
Done ...
```
