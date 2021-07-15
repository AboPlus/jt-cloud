package com.cy.jt.system.service.aspect;

import com.cy.jt.common.annotation.RequiredLog;
import com.cy.jt.system.domain.SysLog;
import com.cy.jt.system.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Aspect Spring AOP 中基于该注解描述切面类型,切面类型可以定义多个切入点和通知方法
 *          1）切入点：要执行扩展业务的一些方法集合(这些方法通常会认为是切入点方法)
 *          2）通知方法：用于封装扩展业务逻辑的方法
 * @author Abo
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    /**
     * @Pointcut("@annotation(com.cy.jt.common.annotation.RequiredLog)")
     * @Pointcut 该注解用于定义切入点
     * @annotation 该注解为一个切入点表达式，表达式中注解描述的方法为切入点方法
     * doLog() 该方法没有意义，其作用只是用来承载上面的注解
     */
    @Pointcut("@annotation(com.cy.jt.common.annotation.RequiredLog)")
    public void doLog(){}

    /**
     * @Around 该注解描述的方法为通知方法，此方法内部可以直接调用目标方法
     *         (就是要织入扩展功能的方法),并且可以在目标方法执行前后添加扩展业务
     *
     * joinPoint 为连接点对象，ProceedingJoinPoint类型只能应用在@Around注解描述的方法参数中
     *          proceed()执行目标方法(自己写的业务方法)，其返回值为目标方法的返回值！！！
     * @param joinPoint
     * @return
     */
    @Around("doLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long t1 = System.currentTimeMillis();
        log.debug("around.before {}",t1);
        try {
            Object result = joinPoint.proceed();
            long t2 = System.currentTimeMillis();
            log.debug("around.after {}",t2);
            doLogInfo(joinPoint, t2-t1,null);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            long t2 = System.currentTimeMillis();
            doLogInfo(joinPoint, t2-t1, e);
            log.error("exception {}",e.getMessage());
            throw e;
        }
    }

    /**
     * 获取用户的详细操作日志
     *          谁(ip + username)
     *          在什么时间
     *          执行了什么操作
     *          访问了哪个方法
     *          传递了什么参数
     *          耗时多长
     */
    private void doLogInfo(ProceedingJoinPoint joinPoint,Long time,Throwable e) throws JsonProcessingException, NoSuchMethodException {
        //1.获取用户操作日志
        //1.1 获取登录ip地址(暂未做登录，给定固定值)
        String ip = "192.168.126.128";
        //1.2 获取登录用户名(暂未做登录，给定固定值)
        String username = "Abo";
        //1.3 获取具体操作(定义在了RequiredLog注解中)
        /* 1.3.1 获取目标类型(代理对象的目标) */
        Class<?> targetCls = joinPoint.getTarget().getClass();
        /* 1.3.2 获取目标方法(类，方法名，参数列表) */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        /* 假如目标方法所在的类实现了接口，那么拿到的方法对象其实是接口中的方法对象，故不推荐使用 */
        //Method method = methodSignature.getMethod();
        String methodName = methodSignature.getName();
        Class[] methodParameterTypes = methodSignature.getParameterTypes();
        Method targetMethod = targetCls.getMethod(methodName, methodParameterTypes);
        /* 1.3.3 获取方法上的RequiredLog注解 */
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        /* 1.3.4 获取方法上的操作名 */
        String operation = requiredLog.operation();
        //1.4 获取方法声明信息
        String targetClsName = targetCls.getName();
        String targetClassMethodName = String.format("%s.%s", targetClsName, methodName);
        //1.5 获取方法执行时的实际参数，并将其转换为json字符串
        Object[] args = joinPoint.getArgs();
        String params = new ObjectMapper().writeValueAsString(args);

        //2.封装操作日志(pojo对象)
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip)
                .setUsername(username)
                .setCreatedTime(new Date())
                .setOperation(operation)
                .setMethod(targetClassMethodName)
                .setParams(params)
                .setTime(time);
        if (e!=null) {
            sysLog.setError(e.getMessage()).setStatus(0);
        }
        //3.输出或记录操作日志
        String logJsonInfo = new ObjectMapper().writeValueAsString(sysLog);
        log.info("log info {}",logJsonInfo);
        //后续将日志写入到数据库
        sysLogService.insertLog(sysLog);
    }


}
