# 系统简介

## 概述

​	京淘权限子系统微服务版jt-cloud，基于Spring Boot、Spring Cloud & Alibaba等技术的前后端分离的后台管理系统。内置模块如公告管理，日志管理，菜单管理，角色管理、部门管理、用户管理等、并支持代码生成等。在线定时任务配置；

## 技术选型

- 后端技术栈

```
Mybatis、Spring、Spring Boot、Spring Cloud & Alibaba、Nacos、Sentinel
```

- 前端VUE技术栈

```
...
```

## 系统服务骨架

```
Jt-cloud-admin
├── jt-api              
├── jt-gateway     				    // 网关模块 [8080]
├── jt-auth        				    // 认证中心 [9100]
├── jt-commons       			    // 通用模块
│       └── jt-common-basic         // 基础资源
├── jt-modules            		    // 业务模块
│       └── jt-protal               // 门户模块 [9081]
│       └── jt-system               // 系统模块 [8091]
├──pom.xml             		        // 公共依赖
```

## 微服务技术架构

![image-20210715132635454](D:\AboStudy\typora笔记\6-msa\京淘权限管理子系统.assets\image-20210715132635454.png)

## 服务分层架构设计

​	系统分层设计是一种设计思想（分而治之），是让每层对象都有一个独立职责，再让多层对象协同（耦合）完成一个完整的功能。这样做可以更好提高系统可扩展性,但同时也会增加系统整体运维的难度。

<img src="D:\AboStudy\typora笔记\6-msa\京淘权限管理子系统.assets\image-20210715132727283.png" alt="image-20210715132727283" style="zoom: 80%;" />

​	其中，在上图中的箭头表示一种直接依赖关系，开放接口层可以依赖于 Web 层，也可以直接依赖于 Service 层，其它依此类推(具体每层要实现的逻辑可自行查阅阿里巴巴开发手册)。
