package com.xliyun.spring.proxy.dynamic2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-11 11:29
 */
public class TestCustomHandler implements CoustomInvocationHandler {

    Object target;

    public TestCustomHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Method method) {
        try {
            System.out.println("TestCustomHandler");
            return method.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
