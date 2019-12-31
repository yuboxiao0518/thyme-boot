package com.thyme.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysMenu;
import com.thyme.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thyme
 * @ClassName SysMenuDao
 * @Description TODO
 * @Date 2019/12/19 15:44
 */
@Repository
public interface SysMenuDao extends BaseMapper<SysMenu> {

    @Select("SELECT *  FROM sys_menu as m LEFT JOIN sys_menu_role as r ON m.id = r.menu_id WHERE m.is_show = '1' and r.role_id = #{roleId} ORDER BY m.menu_weight")
    List<SysMenu> findByRoleId(@Param("roleId") String roleId);


    @Select("SELECT * FROM sys_menu WHERE menu_level = 1 ORDER BY menu_weight")
    IPage<SysMenu> findFirstMenu(Page page);

    @Select("SELECT * FROM sys_menu WHERE parent_id = #{parentId} ORDER BY menu_weight")
    List<SysMenu> findByParentId(@Param("parentId") String parentId);

}
