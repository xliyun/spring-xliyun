package com.xliyun.spring.autowire;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  直接在类中通过ApplicationContext拿到原型模式的bean
 */
@Service("indexService2")
public class IndexService2  implements ApplicationContextAware {

    @Resource(name = "indexDaoImpl")
    private IndexDao indexDaoImpl;

    private ApplicationContext applicationContext;

    public void service(){
        IndexDao dao= (IndexDao) applicationContext.getBean("indexDaoImpl");
        System.out.println("service的hashCode:"+this.hashCode());
        System.out.println("indexDao的hashCode:"+dao.hashCode());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
