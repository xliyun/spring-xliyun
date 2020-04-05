package com.xliyun.spring.autowire;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:35
 */
@Profile("dao2")
@Repository
public class IndexDaoImpl2 implements IndexDao {



    @Override
    public void test() {
        System.out.println("indexDao2");
    }
}
