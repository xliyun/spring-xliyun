package com.xliyun.spring.initialspring;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-25 22:12
 */
@Configuration
@ImportResource("classpath:spring-factorybean.xml")
public class AppConfig {

    @Bean
    @Autowired//spring5之后可以不写
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setPassword("Aa123456");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setUrl("jdbc:mysql://47.93.7.174:3306/ccb_hcf_expense");

        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return driverManagerDataSource;
    }
}
