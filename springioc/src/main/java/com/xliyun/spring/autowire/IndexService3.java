package com.xliyun.spring.autowire;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  直接在类中通过ApplicationContext拿到原型模式的bean
 */
@Service("indexService3")
public class IndexService3 {
    @Lookup(value = "indexDaoImpl")
    public IndexDao getIndexDaoImpl(){
        return null;
    }

    @Resource
    private IndexDao indexDaoImpl;


    public void service(){
        System.out.println("service的hashCode: "+this.hashCode());
        System.out.println("indexDao的hashCode: "+getIndexDaoImpl().hashCode());
    }

}
