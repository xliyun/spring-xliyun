package com.xliyun.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-02 22:35
 */
@Service
public class IndexService {

    @Autowired
    private IndexDao dao;


    public IndexService(){

    }

    public IndexService(IndexDao dao){
        this.dao = dao;
    }

    public void service(){
        dao.test();
    }

    public void setDao(IndexDao dao) {
        this.dao = dao;
    }
}
