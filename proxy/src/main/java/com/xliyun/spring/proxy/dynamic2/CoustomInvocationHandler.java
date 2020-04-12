package com.xliyun.spring.proxy.dynamic2;

import java.lang.reflect.Method;

public interface CoustomInvocationHandler {

    public Object invoke(Method method);
}
