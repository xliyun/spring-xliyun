package com.xliyun.springx.service;

import com.xliyun.springx.dao.UserDao;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-12 10:44
 */
public class UserServiceImpl implements UserService {

    UserDao dao;

    public UserServiceImpl(UserDao userDao){
        this.dao=userDao;
    }

    public void find() {
        System.out.println("service");
        dao.query();
    }
}
