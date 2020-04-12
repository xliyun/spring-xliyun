package com.xliyun.spring.proxy.polymerization;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 16:20
 */
public class UserDaoLog implements UserDao {

    private UserDao dao;

    public UserDaoLog(UserDao dao){
        this.dao=dao;
    }

    @Override
    public void query() {
        System.out.println("日志代理");
        dao.query();
    }
}
