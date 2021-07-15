package com.cy.jt.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abo
 */
@Data
@Accessors(chain = true)
public class SysLog implements Serializable {
    private static final long serialVersionUID = -5396754144386703672L;
    /** 日志id */
    private Integer id;
    /** 访问ip */
    private String ip;
    /** 访问用户 */
    private String username;
    /** 操作名称 */
    private String operation;
    /** 访问方法名 */
    private String method;
    /** 访问方法时传入的参数 */
    private String params;
    /** 耗时 */
    private Long time;
    /** 操作状态，1表示ok，0表示error */
    private Integer status = 1;
    /** 具体错误信息 */
    private String error;
    /** 日志记录时间 */
    private Date createdTime;

}
