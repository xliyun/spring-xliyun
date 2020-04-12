package com.xliyun.spring.proxy.dynamic;
import com.xliyun.spring.proxy.dynamic.UserDao2;
public class $Proxy implements UserDao2 {
	private UserDao2 target;
	public $Proxy (UserDao2 target){
		this.target = target;
	}
	public void query() {
		System.out.println("log");
		target.query();
	}
	public String query(String p) {
		System.out.println("log");
		return target.query(p);
	}
}