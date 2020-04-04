package com.xliyun.spring.autowire;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-03 21:54
 */
//注解加javaconfig加xml混合使用
@Configuration//相当于spring.xml配置文件
@ComponentScan("com.xliyun.spring.autowire")
//@ImportResource("classpath:spring-autowire.xml")//和xml方式混用
public class Spring {
}
