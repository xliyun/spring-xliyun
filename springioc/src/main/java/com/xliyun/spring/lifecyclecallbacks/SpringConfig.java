package com.xliyun.spring.lifecyclecallbacks;

import com.mysql.jdbc.Driver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-03 21:54
 */
//注解加javaconfig加xml混合使用
@Configuration//相当于spring.xml配置文件
@ComponentScan(
        value = "com.xliyun.spring.lifecyclecallbacks"
        //REGEX是正则表达式
        //excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.xliyun.spring.lifecyclecallbacks.service.*")}
)
//@ImportResource("classpath:spring-autowire.xml")//和xml方式混用
public class SpringConfig {

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
