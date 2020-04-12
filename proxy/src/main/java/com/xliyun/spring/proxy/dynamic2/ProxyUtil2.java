package com.xliyun.spring.proxy.dynamic2;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
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
public class ProxyUtil2 {

    /**
     * content ---> String
     * 生成  .java文件
     * 编译.java文件为  .class文件
     * new一个对象
     * @return
     */
    public static Object newInstance(Class targetInf,CoustomInvocationHandler h){
        //通过目标对象，获取目标对象实现的接口类型，这里取的是第0个接口

        Object proxy=null;//代理对象
        String line = "\n";//换行
        String tab = "\t";
        String infName = targetInf.getSimpleName();//简单的类名
        String content = "";
        String packageContect = "package com.xliyun.spring.proxy.dynamic2;"+line;
        String importContent = "import "+targetInf.getName()+";"+line
                                +"import com.xliyun.spring.proxy.dynamic2.CoustomInvocationHandler;"+line
                                +"import java.lang.reflect.Method;"+line
                                +"import java.lang.Exception;"+line;
        String clazzFirstLineContent = "public class $Proxy2 implements "+infName+" {"+line;
        String filedContent = tab+"private CoustomInvocationHandler h;"+line;

        //这里不需要构造方法了
        String constructorContent = tab+"public $Proxy2 (CoustomInvocationHandler h){"+line
                                    +tab+tab+"this.h = h;"+line
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
            +tab+tab+"try {"+line
                    //TODO 传递参数
            +tab+tab+"Method method = Class.forName(\""+targetInf.getName()+"\").getDeclaredMethod(\""+methodName+"\");"+line
            +tab+tab+"return ("+resultTypeName+")h.invoke(method);"+line
            +tab+tab+"} catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }"+line
                    +tab+"return null;"+line
//            if(resultTypeName.equals("void")){
//                methodContent+=tab+tab+"target."+methodName+"("+paramsContent+");"+line
//                        +tab+"}"+line;
//            }else {
//                methodContent+=tab+tab+"return target."+methodName+"("+paramsContent+");"+line
//                        +tab+"}"+line;
//            }
            +tab+"}"+line;



        }

        content=packageContect+importContent+clazzFirstLineContent+filedContent+constructorContent+methodContent+"}";

        File file=new File("D:\\github\\springxliyun\\proxy\\src\\main\\java\\com\\xliyun\\spring\\proxy\\dynamic2\\$Proxy2.java");

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
        Class clazz = urlClassLoader.loadClass("com.xliyun.spring.proxy.dynamic2.$Proxy2");

        //根据参数获取构造方法
        Constructor constructor = clazz.getConstructor(CoustomInvocationHandler.class);
        //我们的目标对象也就是target是代理对象构造函数的参数,返回代理对象
        proxy = constructor.newInstance(h);

    }catch (Exception e){
        e.printStackTrace();
    }


        return proxy;
    }
}
