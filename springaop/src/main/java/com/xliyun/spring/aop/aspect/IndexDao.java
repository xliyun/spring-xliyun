package com.xliyun.spring.aop.aspect;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-05 15:59
 */
@Repository("indexDao")
@Scope("prototype")
public class IndexDao implements Dao {

    public void query(String str){
        System.out.println("query");
        System.out.println(str);
    }

    @customizeAnnotation
    public void query2(){
        System.out.println("query2");
    }
}
