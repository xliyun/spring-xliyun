package com.xliyun.spring.lifecyclecallbacks;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-04 16:55
 */
@Repository
public class IndexDaoImpl1 implements IndexDao {

    public IndexDaoImpl1(){
        System.out.println("Constructor");
    }

    @PostConstruct
    public void initMethod(){
        System.out.println("使用注解 ==> 初始化bean时调用");
    }

    @PreDestroy
    public void destroyMethod(){
        System.out.println("使用注解 ==> 销毁bean时调用");
    }
}
