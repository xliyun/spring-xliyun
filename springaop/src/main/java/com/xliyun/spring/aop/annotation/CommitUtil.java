package com.xliyun.spring.aop.annotation;

import com.xliyun.spring.aop.annotation.Entity;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-05 12:13
 */
public class CommitUtil {

    /**
     * 通过一个对象构建一条查询sql
     * @param object
     */
    public static String buildQuerySqlForEntiry(Object object){

        Class clazz = object.getClass();

        String entityValue = "";
        //1.是否加了注解
        if(clazz.isAnnotationPresent(Entity.class)){
            //2.得到注解的值
            Entity entity = (Entity) clazz.getAnnotation(Entity.class);
            //3.调用方法
            entityValue = entity.value();

        }
        String sql = "select * from " + entityValue;
        return sql;
    }
}
