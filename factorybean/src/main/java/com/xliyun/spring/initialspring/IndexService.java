package com.xliyun.spring.initialspring;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-25 22:27
 */
@Service("indexService")
public class IndexService {
    public void test(){
        System.out.println("哈喽！");
    }
}
