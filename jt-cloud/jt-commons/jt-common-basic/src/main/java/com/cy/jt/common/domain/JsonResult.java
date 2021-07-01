package com.cy.jt.common.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 3279770143142198890L;
    /**响应状态码*/
    private int code=1;//ok,0->error
    /**具体消息*/
    private String message="ok";
    /**一般对应查询结果,可以将查询结果赋值给此属性*/
    private Object data;
    public JsonResult(){}
    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Object data){
        this.data=data;
    }
    public JsonResult(Throwable e){
        this.code=0;
        this.message=e.getMessage();
    }
    //........
}