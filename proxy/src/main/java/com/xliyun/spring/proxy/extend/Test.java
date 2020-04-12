package com.xliyun.spring.proxy.extend;

import com.xliyun.spring.proxy.extend.UserDaoImpl;
import com.xliyun.spring.proxy.extend.UserDaoPowerImpl;
import com.xliyun.spring.proxy.polymerization.UserDao;
import com.xliyun.spring.proxy.polymerization.UserDaoLog;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 15:37
 */
public class Test {
    public static void main(String[] args) {
        UserDaoImpl dao =new UserDaoPowerImpl();

        dao.query();

        ///

        UserDao target = new com.xliyun.spring.proxy.polymerization.UserDaoImpl();
        UserDao proxy = new UserDaoLog(target);
        proxy.query();

        //
    }
}
