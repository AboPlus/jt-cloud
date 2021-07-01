package com.cy.jt.system.feign;

import com.cy.jt.common.domain.JsonResult;
import com.cy.jt.system.domain.SysNotice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="jt-system",contextId = "remoteNoticeService")
public interface RemoteNoticeService {

    @GetMapping("/notice/")
    JsonResult doSelectNotices(SysNotice notice);
}
