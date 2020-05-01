package com.xliyun.springx.org.spring.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  自动加载
 *  如果是byType模式，在xml中的所有类都不需要从子标签判断依赖，
 */
public class BeanFactory {

    private String defaultAutowire="default";
    private Map<String,Object> map = new HashMap<String,Object>();

    public BeanFactory(String xml){
        parseXml(xml);
    }

    public void parseXml(String xml) {

        String path = this.getClass().getResource("/").getPath()+xml;
        File file = new File(path);
        SAXReader reader = new SAXReader();
        try {
            Document document= reader.read(file);
            Element rootElement = document.getRootElement();
            String defaultAutowireStr = rootElement.attribute("default-autowire").getValue();
            if(defaultAutowireStr!=null && ("byType".equals(defaultAutowireStr) || "byName".equals(defaultAutowireStr) || "default".equals(defaultAutowireStr))){
                this.defaultAutowire=defaultAutowireStr;
            }

            List<Element> elementAllChil = rootElement.elements();

            for(Iterator<Element> itFirst =rootElement.elementIterator();itFirst.hasNext();){
                /**
                 * 实例化对象
                 */
                Element elementFirstChil = itFirst.next();
                Attribute attributeId = elementFirstChil.attribute("id");
                String beanName = attributeId.getValue();

                Attribute attributeClass = elementFirstChil.attribute("class");
                String className = attributeClass.getValue();
                Object object = null;
                try {
                    //根据注册的类名获该类的Class对象
                    Class clazz = Class.forName(className);
                /**
                 * 维护依赖关系
                 * 看这个对象有没有依赖（判断是否有property属性，或者判断类是否有属性）
                 * 如果有则注入
                 */
                //如果没有子标签，直接加入到bean容器中
                if(elementFirstChil.elements().isEmpty()){
                    object = Class.forName(className).newInstance();
                }else {
                    for (Iterator<Element> itSecond = elementFirstChil.elementIterator(); itSecond.hasNext(); ) {
                        //得到ref的value，通过value得到对象(map当中)
                        //得到name值，然后根据值获取一个Filed的对象
                        //通过field的set方法，set那个对象
                        // 得到 <property name="dao" ref="dao"></property>
                        Element elementSecondChil = itSecond.next();
                        if ("property".equals(elementSecondChil.getName())) {

                            //生成<property>标签里依赖的类对象
                            object = Class.forName(className).newInstance();

                            Attribute refAttribute = elementSecondChil.attribute("ref");
                            String refValue = refAttribute.getValue();
                            //根据ref的名字，去map找依赖的类
                            Object injectDaoObject = map.get(refValue);

                            String nameValue = elementSecondChil.attribute("name").getValue();
                            Field field = clazz.getDeclaredField(nameValue);
                            field.setAccessible(true);
                            field.set(object, injectDaoObject);
                        } else if ("constructor-arg".equals(elementSecondChil.getName())) {
                            Attribute refAttribute = elementSecondChil.attribute("ref");
                            String refValue = refAttribute.getValue();
                            //根据ref的名字，去map找依赖的类
                            Object injectDaoObject = map.get(refValue);
                            //获取<constructor-arg>标签
                            Class injectDaoObjectclazz = injectDaoObject.getClass();

                            //获取构造参数中，参数名为dao的构造函数
                            Constructor[] constructors = clazz.getConstructors();

                            Constructor constructor = clazz.getConstructor(injectDaoObjectclazz.getInterfaces()[0]);
                            object = constructor.newInstance(injectDaoObject);

                        }
                    }
                }

                //这里是说xml中手动装配了，就不需要自动装配byType了
                if(object==null && "byType".equals(this.defaultAutowire)){
                    //判断是否有依赖
                    Field[] declaredFields = clazz.getDeclaredFields();
                    for (Field field : declaredFields) {
                        //得到属性的类型
                        Class<?> injectFileclazz = field.getType();
                        /**
                         * byType模式下，需要遍历map当中的所有对象，判断对象的类型是否和injectObjectClazz相同
                         */
                        int count = 0;
                        Object injectObject=null;
                        for (Map.Entry<String, Object> m : map.entrySet()) {
                            //忽略类自己在map中的键值对
                            if(!m.getKey().equals(beanName)){
                                Class temp = m.getValue().getClass().getInterfaces()[0];
                                if(temp.getName().equals(injectFileclazz.getName())){
                                    injectObject = m.getValue();
                                    //记录找到一个，因为可能找到多个
                                    count++;
                                }
                            }
                        }
                        if(count>1){
                            throw new XliyunSpringException("需要一个对象，但是找到了两个");
                        }else {
                            object = Class.forName(className).newInstance();
                            //完成注入
                            field.setAccessible(true);
                            field.set(object,injectObject);
                        }

                    }
                }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                map.put(beanName,object);
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        System.out.println(map);

    }

    public Object getBean(String beanName){

        return map.get(beanName);
    }
}
