package com.xliyun.spring.proxy.dynamic2;
import com.xliyun.spring.proxy.dynamic2.XliyunDao;
import com.xliyun.spring.proxy.dynamic2.CoustomInvocationHandler;
import java.lang.reflect.Method;
import java.lang.Exception;
public class $Proxy2 implements XliyunDao {
	private CoustomInvocationHandler h;
	public $Proxy2 (CoustomInvocationHandler h){
		this.h = h;
	}
	public String proxy() {
		try {
		Method method = Class.forName("com.xliyun.spring.proxy.dynamic2.XliyunDao").getDeclaredMethod("proxy");
		return (String)h.invoke(method);
		} catch (Exception e) {
            e.printStackTrace();
        }
	return null;
	}
}