package com.xliyun.spring.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:34
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Spring.class);

       IndexService service= (IndexService) annotationConfigApplicationContext.getBean("indexService");
        service.service();
        IndexService service2= (IndexService) annotationConfigApplicationContext.getBean("indexService");
        service.service();
        System.out.println(service.hashCode());
        System.out.println(service2.hashCode());

    }
}
