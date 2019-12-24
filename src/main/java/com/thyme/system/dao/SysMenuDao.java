package com.thyme.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}
