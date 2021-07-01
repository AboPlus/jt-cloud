package com.cy.jt.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告領域對象,与表中字段有对应关系,可以基于此对象
 * 存储从数据库查询到的数据.
 * 记住:java中所有用于存储数据的对象都让它实现Serializable接口,
 * 并且添加序列化id
 */
@Data
public class SysNotice implements Serializable {
    private static final long serialVersionUID = 8878298269710627491L;
    private Integer id;
    private String title;
    private String type;
    private String content;
    private String status;
    private String remark;
    private String createdUser;
    private String modifiedUser;
    private Date createdTime;
    private Date modifiedTime;
}
