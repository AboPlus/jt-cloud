package com.cy.jt.system.web.controller;
import com.cy.jt.common.annotation.RequiredLog;
import com.cy.jt.common.domain.JsonResult;
import com.cy.jt.system.domain.SysNotice;
import com.cy.jt.system.service.SysNoticeService;
import com.cy.jt.system.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * @CacheEvict 用来标注在需要清除缓存元素的方法或类上的，触发方法会清除缓存
     * allEntries = true ：allEntries是boolean类型，表示是否需要清除缓存中的所有元素。
     *          默认为false，表示不需要。当指定了allEntries为true时，Spring Cache将忽略指定的key。
     * beforeInvocation = false ：清除操作默认是在对应方法成功执行之后触发的，
     *          即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     *          使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，
     *          Spring会在调用该方法之前清除缓存中的指定元素。
     * @param notice
     * @return
     */
     @CacheEvict(value = "notices",allEntries = true,beforeInvocation = false)
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

     @RequiredLog(operation = "基于查询通告信息")
     @Cacheable(value = "notices")
     @GetMapping("{id}")
     public JsonResult doSelectById(@PathVariable Long id){
        return new JsonResult(sysNoticeService.selectById(id));
     }

    /**
     * @Cacheable 注解描述的方法在执行时，系统低层会先去查询缓存（需要在启动类或配置类上加上@EnableCaching注解）
     * @Cacheable(value = "notices")这里的notices表示的是缓存的名称，而不是key
     * 被@RequiredLog注解标识的方法叫做切入点方法
     * @param sysNotice
     * @return
     */
     @RequiredLog(operation = "查询通告信息")
     @Cacheable(value = "notices")
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
         System.out.println("select notice's thread is : " + Thread.currentThread().getName());
         return new JsonResult(PageUtils.startPage()//启动分页拦截器
                    .doSelectPageInfo(()->{//执行查询业务
                      sysNoticeService.selectNotices(sysNotice); }));
     }
}

