package com.xliyun.spring.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-08 22:55
 */
public class TestInvocationHandler implements InvocationHandler {
    Object target;

    public TestInvocationHandler(Object target){
        this.target=target;
    }
    /**
     *
     * @param proxy 代理对象
     * @param method 目标方法
     * @param args   目标方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("TestInvocationHandler jdk");
        //就是方法的反射的用法，目标对象，方法的参数
        method.invoke(target,args);
        return null;
    }
}
