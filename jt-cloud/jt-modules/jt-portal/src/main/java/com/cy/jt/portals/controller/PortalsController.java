package com.cy.jt.portals.controller;
import com.cy.jt.common.domain.JsonResult;
import com.cy.jt.system.domain.SysNotice;
import com.cy.jt.system.feign.RemoteNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PortalsController {
    @Autowired
    private RemoteNoticeService remoteNoticeService;
    @GetMapping("/portals/notice/")
    public JsonResult doSelectNotice(SysNotice notice){
        //如何调用notice服务呢?
        return remoteNoticeService.doSelectNotices(notice);
    }
}
