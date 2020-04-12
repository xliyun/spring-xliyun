package com.xliyun.spring.proxy.dynamic;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 16:11
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void query() {
        System.out.println("假装查询数据库");
    }
}
