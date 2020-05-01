package com.xliyun.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-25 14:19
 */

public class TestFactoryBean {
    public static void main(String[] args) {
        //beanfactory就是产生bean的工厂
        //BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //获取被FactoryBean返回的类
        TempDaoFacotryBean tempDaoFacotryBean = (TempDaoFacotryBean) annotationConfigApplicationContext.getBean("daoFacotryBean");
        tempDaoFacotryBean.test();
        System.out.println(tempDaoFacotryBean.getMsg1());

        //获取FactoryBean
        DaoFacotryBean daoFacotryBean = (DaoFacotryBean) annotationConfigApplicationContext.getBean("&daoFacotryBean");
        daoFacotryBean.testBean();

    }

}
