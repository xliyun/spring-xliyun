package com.xliyun.spring.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:34
 */
public class TestXml {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
       IndexService service= (IndexService) classPathXmlApplicationContext.getBean("service");
        service.service();
    }
}
