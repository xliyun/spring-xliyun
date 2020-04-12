package com.xliyun.spring.aop.annotation;

import com.xliyun.spring.aop.annotation.Entity;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-05 12:11
 */
@Entity(value = "city")
public class CityEntity {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
