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

       IndexService2 service21= (IndexService2) annotationConfigApplicationContext.getBean("indexService2");
        service21.service();
        IndexService2 service22= (IndexService2) annotationConfigApplicationContext.getBean("indexService2");
        service22.service();

        System.out.println("=====原型的bean和单例的hashCode不一样==========");


        IndexService3 service31= (IndexService3) annotationConfigApplicationContext.getBean("indexService3");
        service31.service();
        IndexService3 service32= (IndexService3) annotationConfigApplicationContext.getBean("indexService3");
        service32.service();

        System.out.println("===================");
        IndexService4 service41= (IndexService4) annotationConfigApplicationContext.getBean("indexService4");
        service41.service();
        IndexService4 service42= (IndexService4) annotationConfigApplicationContext.getBean("indexService4");
        service42.service();
    }
}
