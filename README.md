[FindJar](https://github.com/baininghan/findJar)
---

此类用于遍历一个文件目录下可能存在的所有压缩包(.jar)中是否存在指定的Class文件。当然也可以，查找任何后缀名的文件

#### 在命令行执行

```java
java -jar findJar.jar
```

页面信息：

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
