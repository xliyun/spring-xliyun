package com.xliyun.spring.initialspring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-25 23:18
 */
public class TestIinitialSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =new AnnotationConfigApplicationContext();


        //annotationConfigApplicationContext.getEnvironment().setActiveProfiles();



        //===========手动注册bean
        //AnnotationConfigApplicationContext(AppConfig.class)的构造方法里面就是register
        //如果一开始用了AnnotationConfigApplictaionContext(AppConfig.class)初始化过，再用refresh()就会报错，因为一开始bean已经初始化到了容器里面
        annotationConfigApplicationContext.register(AppConfig.class);
        annotationConfigApplicationContext.scan("com.xliyun");//扫描包的路径
        annotationConfigApplicationContext.register(IndexService.class);
        annotationConfigApplicationContext.refresh();

        String name = annotationConfigApplicationContext.getBean(IndexService.class).getClass().getName();
        System.out.println(name);
    }
}
