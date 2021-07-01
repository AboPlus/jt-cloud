package com.cy.jt.system.web.controller;
import com.cy.jt.common.domain.JsonResult;
import com.cy.jt.system.domain.SysNotice;
import com.cy.jt.system.service.SysNoticeService;
import com.cy.jt.system.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice/")
public class SysNoticeController {
     @Autowired
     private SysNoticeService sysNoticeService;

     @PostMapping
     public JsonResult doInsertNotice(@RequestBody SysNotice notice){
         sysNoticeService.insertNotice(notice);
         return new JsonResult("insert ok");
     }

     @PutMapping
     public JsonResult doUpdateNotice(@RequestBody SysNotice notice){
        sysNoticeService.updateNotice(notice);
        return new JsonResult("update ok");
     }

     @DeleteMapping("{id}")
     public JsonResult doDeleteById(@PathVariable Long ...id){
         sysNoticeService.deleteById(id);
         return new JsonResult("delete ok");
     }

     @GetMapping("{id}")
     public JsonResult doSelectById(@PathVariable Long id){
        return new JsonResult(sysNoticeService.selectById(id));
     }

     @GetMapping
     public JsonResult doSelectNotices(SysNotice sysNotice){
        // return new JsonResult(sysNoticeService.selectNotices(sysNotice));

//         return new JsonResult(PageHelper.startPage(1,2).doSelectPageInfo(new ISelect() {
//             @Override
//             public void doSelect() {
//                 sysNoticeService.selectNotices(sysNotice);
//             }
//         }));
         //上面分页的简化写法就是如下方式
         return new JsonResult(PageUtils.startPage()//启动分页拦截器
                    .doSelectPageInfo(()->{//执行查询业务
                      sysNoticeService.selectNotices(sysNotice); }));
     }
}

