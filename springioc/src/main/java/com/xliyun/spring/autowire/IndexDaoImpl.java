package com.xliyun.spring.autowire;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:35
 */
@Repository
@Scope("prototype")
public class IndexDaoImpl implements IndexDao {

    @Override
    public void test() {
        System.out.println("indexDao1");
    }
}
