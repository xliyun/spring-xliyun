package com.xliyun.springx.test;

import com.xliyun.springx.org.spring.util.BeanFactory;
import com.xliyun.springx.service.UserService;
import com.xliyun.springx.service.UserServiceImpl;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-12 10:45
 */
public class Test {
    public static void main(String[] args) {
        BeanFactory beanFactory =new BeanFactory("spring.xml");
        UserService service =(UserService)beanFactory.getBean("service");
        service.find();
    }
}
