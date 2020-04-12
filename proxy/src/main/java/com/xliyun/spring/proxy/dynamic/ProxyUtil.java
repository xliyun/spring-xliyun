package com.xliyun.spring.proxy.dynamic;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
package com.google;
import com.xliyun.spring.proxy.dynamic.UserDao

public class UserDaoLog implements UserDao {
 private UserDao target;
 public UserDaoLog(UserDao target){
    this.target=target;
 }

 @Override
 public void query() {
 System.out.println("日志代理");
 dao.query();
 }
 }
 */
public class ProxyUtil {

    /**
     * content ---> String
     * 生成  .java文件
     * 编译.java文件为  .class文件
     * new一个对象
     * @return
     */
    public static Object newInstance(Object target){
        //通过目标对象，获取目标对象实现的接口类型，这里取的是第0个接口
        Class targetInf = target.getClass().getInterfaces()[0];
        Object proxy=null;//代理对象
        String line = "\n";//换行
        String tab = "\t";
        String infName = targetInf.getSimpleName();//简单的类名
        String content = "";
        String packageContect = "package com.xliyun.spring.proxy.dynamic;"+line;
        String importContent = "import "+targetInf.getName()+";"+line;
        String clazzFirstLineContent = "public class $Proxy implements "+infName+" {"+line;
        String filedContent = tab+"private "+infName+" target;"+line;
        String constructorContent = tab+"public $Proxy ("+infName+" target){"+line
                                    +tab+tab+"this.target = target;"+line
                                    +tab+"}"+line;

        /**
         *  public UserDaoLog(UserDao target){
         *  this.target=target;
         *  }
         */
        Method methods[] = targetInf.getDeclaredMethods();//获取方法

        //public void query() {
        String methodContent = "";
        for (Method method : methods) {
            String resultTypeName = method.getReturnType().getSimpleName();//返回类型名
            String methodName = method.getName();//方法名

            //获取参数名
            Class args[] = method.getParameterTypes();//参数类型,返回的是类
            String argsContenct ="";//参数
            String paramsContent = "";//形参名
            int flag=0;
            for (Class arg : args) {
                String temp = arg.getSimpleName();
                //String p0,String p1,String p2 ...
                argsContenct+=temp+" p"+flag+",";
                paramsContent+="p"+flag+",";
                flag++;
            }
            if(argsContenct.length()>0){
                argsContenct=argsContenct.substring(0,argsContenct.lastIndexOf(",")-1);
                paramsContent=paramsContent.substring(0,paramsContent.lastIndexOf(",")-1);

            }


            methodContent+=tab+"public "+resultTypeName+" "+methodName+"("+argsContenct+") {"+line
            +tab+tab+"System.out.println(\"log\");"+line;
            if(resultTypeName.equals("void")){
                methodContent+=tab+tab+"target."+methodName+"("+paramsContent+");"+line
                        +tab+"}"+line;
            }else {
                methodContent+=tab+tab+"return target."+methodName+"("+paramsContent+");"+line
                        +tab+"}"+line;
            }



        }

        content=packageContect+importContent+clazzFirstLineContent+filedContent+constructorContent+methodContent+"}";

        File file=new File("D:\\github\\springxliyun\\proxy\\src\\main\\java\\com\\xliyun\\spring\\proxy\\dynamic\\$Proxy.java");

    try {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();

        //动态编译文件,上古代码，现在不怎么用了
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,null,null);
        Iterable units = fileMgr.getJavaFileObjects(file);

        JavaCompiler.CompilationTask t = compiler.getTask(null,fileMgr,null,null,null,units);
        t.call();
        fileMgr.close();

        //Class.forName() 利用Classloader生成对象
        URL[] urls = new URL[]{new URL("file:D:\\\\github\\springxliyun\\proxy\\src\\main\\java\\")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class clazz = urlClassLoader.loadClass("com.xliyun.spring.proxy.dynamic.$Proxy");

        //根据参数获取构造方法
        Constructor constructor = clazz.getConstructor(targetInf);
        //我们的目标对象也就是target是代理对象构造函数的参数,返回代理对象
        proxy = constructor.newInstance(target);

    }catch (Exception e){
        e.printStackTrace();
    }


        return proxy;
    }
}
