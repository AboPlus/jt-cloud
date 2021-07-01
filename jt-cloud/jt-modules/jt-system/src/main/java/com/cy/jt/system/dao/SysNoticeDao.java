package com.cy.jt.system.dao;
import com.cy.jt.system.domain.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据访问逻辑接口定义
 */
@Mapper
public interface SysNoticeDao {//com.cy.jt.system.dao.SysNoticeDao
    /**
     * 基于id查找notice信息
     * @param id
     * @return
     */
    //@Select("select * from sys_notice where id=#{id}")
    SysNotice selectById(Long id);
    /**
     * 更新通告信息
     * @param notice
     * @return
     */
    int updateNotice(SysNotice notice);
    /**
     * 新增通告信息
     * @param notice
     * @return
     */
    int insertNotice(SysNotice notice);
    /**
     * 基于id执行删除操作
     * @param id 要删除的记录id
     * @return 删除的行数
     */
    int deleteById(Long ...id);
    /**
     * 基于条件查询公告信息
     * @param notice 基于此对象封装查询参数
     * @return 查询到的公告列表数据
     */
     List<SysNotice> selectNotices(SysNotice notice);
}
