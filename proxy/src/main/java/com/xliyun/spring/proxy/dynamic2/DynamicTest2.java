package com.xliyun.spring.proxy.dynamic2;


import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 21:12
 */
public class DynamicTest2 {
    public static void main(String[] args) {

//        //自己写的动态代理
//        XliyunDao xliyunDao = new XliyunDaoImpl2();
//        XliyunDao proxy = (XliyunDao) ProxyUtil2.newInstance(XliyunDao.class,new TestCustomHandler(new XliyunDaoImpl2()));
//        proxy.proxy();
//
//        //jdk动态代理
//        XliyunDao jdkproxy = (XliyunDao) Proxy.newProxyInstance(DynamicTest2.class.getClassLoader(),new Class[]{XliyunDao.class},new TestInvocationHandler(new XliyunDaoImpl2()));
//        jdkproxy.proxy();

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy18", new Class[]{XliyunDao.class});

        File file = new File("D:\\github\\springxliyun\\proxy\\src\\main\\java\\com\\xliyun\\spring\\proxy\\dynamic2\\$Proxy18");
        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes);
            fo.flush();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
