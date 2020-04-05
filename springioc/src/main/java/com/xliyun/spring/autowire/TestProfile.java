package com.xliyun.spring.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:34
 */
public class TestProfile {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        //选择profile的环境
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles("dao1");
        annotationConfigApplicationContext.register(Spring.class);
        //重新扫描一遍
        annotationConfigApplicationContext.refresh();

       String indexDaoName = annotationConfigApplicationContext.getBean(IndexDao.class).getClass().getSimpleName();
        System.out.println(indexDaoName);

    }
}
