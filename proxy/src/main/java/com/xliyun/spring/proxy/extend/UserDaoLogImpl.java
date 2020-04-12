package com.xliyun.spring.proxy.extend;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 15:52
 */
public class UserDaoLogImpl extends UserDaoImpl {

    @Override
    public void query(){
        System.out.println("------log------");
        super.query();
    }
}
