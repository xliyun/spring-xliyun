package com.xliyun.spring.aop.annotation;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-05 12:11
 */
public class Test {
    public static void main(String[] args) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId("1");
        cityEntity.setName("test");
        String sql = CommitUtil.buildQuerySqlForEntiry(cityEntity);
        //select * from city where id = '1' and name = 'test'
        System.out.println(sql);
    }
}
