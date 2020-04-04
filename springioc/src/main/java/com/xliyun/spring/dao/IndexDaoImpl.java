package com.xliyun.spring.dao;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:35
 */
public class IndexDaoImpl implements IndexDao {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
