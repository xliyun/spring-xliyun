package com.xliyun.spring.autowire;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  官方介绍的方法
 */
@Service("indexService4")
public abstract class IndexService4 {

    @Lookup(value = "indexDaoImpl")
    protected abstract IndexDao getIndexDaoImpl();

    public void service(){
        System.out.println("service的hashCode: "+this.hashCode());
        System.out.println("indexDao的hashCode: "+getIndexDaoImpl().hashCode());
    }

}
