package com.xliyun.spring.proxy.polymerization;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 20:38
 */
public class UserTimerDao implements UserDao {
    UserDao dao;

    public UserTimerDao(UserDao dao){
        this.dao=dao;
    }

    @Override
    public void query() {
        System.out.println("时间代理");
        dao.query();
    }
}
