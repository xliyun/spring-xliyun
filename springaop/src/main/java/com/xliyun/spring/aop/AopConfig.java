package com.xliyun.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-05 15:57
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)//true是cglib代理 false是jdk动态代理
@ComponentScan("com.xliyun.spring.aop")
@ImportResource("classpath:spring.xml")
public class AopConfig {
}
