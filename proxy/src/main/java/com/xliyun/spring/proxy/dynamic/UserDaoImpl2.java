package com.xliyun.spring.proxy.dynamic;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 16:11
 */
public class UserDaoImpl2 implements UserDao2{
    @Override
    public void query() {
        System.out.println("假装查询数据库");
    }

    @Override
    public String query(String str) {
        System.out.println(str);
        return str;
    }
}
