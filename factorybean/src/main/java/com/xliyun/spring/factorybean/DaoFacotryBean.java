package com.xliyun.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 如果你的类实现了FactoryBean
 * 那么spring容器当中存在两个对象
 * 一个叫getObject()返回的对象
 * 还有一个是当前对象
 *
 * getObject得到的对象存的是当前类指定的名字
 * 当前对象是 "&"+当前类的名字
 */
public class DaoFacotryBean implements FactoryBean {

    String msg = "1,2,3";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void testBean(){
        System.out.println("DaoFacotryBean");
    }
    @Override
    public Object getObject() throws Exception {
        TempDaoFacotryBean tempDaoFacotryBean = new TempDaoFacotryBean();
        String[] arr = msg.split(",");
        tempDaoFacotryBean.setMsg1(arr[0]);
        tempDaoFacotryBean.setMsg2(arr[1]);
        tempDaoFacotryBean.setMsg3(arr[2]);
        return tempDaoFacotryBean;
    }

    @Override
    public Class<?> getObjectType() {
        return TempDaoFacotryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
