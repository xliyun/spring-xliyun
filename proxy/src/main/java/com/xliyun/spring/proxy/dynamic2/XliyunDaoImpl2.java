package com.xliyun.spring.proxy.dynamic2;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-07 16:11
 */
public class XliyunDaoImpl2 implements XliyunDao {

    @Override
    public String proxy() {
        System.out.println("XliyunDaoImpl2");
        return "XliyunDaoImpl2";
    }
}
