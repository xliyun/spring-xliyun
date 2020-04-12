package com.xliyun.spring.proxy.dynamic;


import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 21:12
 */
public class DynamicTest {
    public static void main(String[] args) {
//        UserDao target = new UserDaoImpl();
//        UserDao proxy = (UserDao) ProxyUtil.newInstance(target);
//        proxy.query();

        UserDao2 target2 = new UserDaoImpl2();
        UserDao2 proxy2 = (UserDao2)ProxyUtil.newInstance(target2);
        String returnStr = proxy2.query("测试");
        System.out.println(returnStr);

        UserDao2 jdkproxy = (UserDao2) Proxy.newProxyInstance(DynamicTest.class.getClassLoader(),new Class[]{UserDao2.class},new TestInvocationHandler(new UserDaoImpl2()));
        jdkproxy.query("jdk动态代理");
    }
}
