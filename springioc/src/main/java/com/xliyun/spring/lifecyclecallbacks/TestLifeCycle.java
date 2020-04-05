package com.xliyun.spring.lifecyclecallbacks;

import com.xliyun.spring.autowire.Spring;
import com.xliyun.spring.lifecyclecallbacks.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-04 17:16
 */
public class TestLifeCycle {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        String indexService = annotationConfigApplicationContext.getBean(IndexService.class).getClass().getSimpleName();

        System.out.println(indexService);
    }
}
