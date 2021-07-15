package com.cy.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @EnableAsync 启动异步机制，加了该注解后，系统初始化时会构建一个线程池，之后在需要异步的方法上加@Async注解即可
 * @EnableCaching 项目初始化时，初始化缓存
 * @author Abo
 */
//@EnableCaching
@EnableAsync
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}
