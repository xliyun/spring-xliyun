package com.xliyun.spring.proxy.polymerization;



/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 15:37
 */
public class Test {
    public static void main(String[] args) {
        UserDao target = new UserDaoImpl();
        UserDao proxy = new UserDaoLog(target);
        proxy.query();
        System.out.println("===============");
        //按照聚合方式的日期代理 和 日志代理按照组合顺序使用，如果是使用继承的代理方式，每一种组合需要新建一个类
        UserDao target2 = new UserDaoLog(new UserDaoImpl());
        UserDao proxy2 = new UserTimerDao(target2);
        proxy2.query();
    }
}
