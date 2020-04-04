package com.xliyun.spring.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:34
 */
public class Test2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Spring.class);

       IndexService2 service= (IndexService2) annotationConfigApplicationContext.getBean("indexService2");
        service.service();
        IndexService2 service2= (IndexService2) annotationConfigApplicationContext.getBean("indexService2");
        service.service();

    }
}
