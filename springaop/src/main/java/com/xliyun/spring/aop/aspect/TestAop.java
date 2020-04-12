package com.xliyun.spring.aop.aspect;

import com.xliyun.spring.aop.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-05 15:59
 */
public class TestAop {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =new AnnotationConfigApplicationContext(AopConfig.class);
        Dao dao = (Dao)annotationConfigApplicationContext.getBean("indexDao");
        //如果是jdk动态代理，target和proxy不等于，target和proxy的接口是相等的，因为jdk动态代理基于接口，
        // cglib动态代理就等于
        System.out.println(dao instanceof IndexDao);
        System.out.println(dao instanceof Proxy);
        dao.query("哈哈哈");
        System.out.println("---------------------------------");
        dao.query2();

//        Class<?>[] interfaces = new Class[]{Dao.class};
//        byte bytes[]= ProxyGenerator.generateProxyClass("proxyClazz",interfaces);
//        File file = new File("D:\\github\\springxliyun\\springaop\\src\\main\\java\\com\\xliyun\\spring\\aop\\aspect\\Test.class");
//        try {
//            FileOutputStream fw = new FileOutputStream(file);
//            fw.write(bytes);
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Dao  dao1 = (Dao) annotationConfigApplicationContext.getBean("indexDao");
        dao1.query2();

    }
}
