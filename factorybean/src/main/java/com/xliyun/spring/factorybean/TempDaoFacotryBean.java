package com.xliyun.spring.factorybean;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-04-25 14:22
 */
public class TempDaoFacotryBean {

    private String msg1;

    private String msg2;

    private String msg3;

    public void test(){
        System.out.println("Facotry Bean ");
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }
}
