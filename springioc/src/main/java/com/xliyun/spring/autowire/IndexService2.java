package com.xliyun.spring.autowire;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  @Autowired
  注解里面用@Autowired是默认使用byType方式的，如果一个接口有多个实现，spring在自动装载类的时候会报错。
  byType没找到的话会根据属性名去找，比如有IndexDaoImpl和IndexDaoImpl2两个实现，一开始会根据类型去找，有两个实现，再按照属性名去找IndexDaoImpl

 @Resource
 @Resource默认是根据属性名字去匹配，比如有IndexDaoImpl和IndexDaoImpl2两个实现，spring就会根据属性名去加载IndexDaoImpl
 当然@Resource也可以直接指定类型@Resource(type = IndexDaoImpl.class)
 也可以指定name，比如IndexDaoImpl的beanName就是首字符变为小写的indexDaoImpl
 */
@Service("indexService2")
public class IndexService2 {

    @Resource(name = "indexDaoImpl")
    private IndexDao indexDaoImpl;

    public void service(){
        System.out.println("service的hashCode:"+this.hashCode());
        System.out.println("indexDao的hashCode:"+indexDaoImpl.hashCode());
    }
}
