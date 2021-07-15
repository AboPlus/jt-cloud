package com.cy.jt.system.service.impl;

import com.cy.jt.system.dao.SysLogDao;
import com.cy.jt.system.domain.SysLog;
import com.cy.jt.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Abo
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public List<SysLog> selectLogs(SysLog sysLog) {
        return sysLogDao.selectLogs(sysLog);
    }

    @Override
    public SysLog selectById(Long id) {
        return sysLogDao.selectById(id);
    }

    /**
     * @Async 由该注解描述的方法为异步切入点方法，需要在启动类上加上@EnableAsync注解
     * @param sysLog
     */
    @Async
    @Override
    public void insertLog(SysLog sysLog) {
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("insert log's thread is " + Thread.currentThread().getName());
        sysLogDao.insertLog(sysLog);
    }

    @Override
    public int deleteById(Long... id) {
        return sysLogDao.deleteById(id);
    }

}
