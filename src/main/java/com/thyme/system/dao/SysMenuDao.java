package com.thyme.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysMenu;
import com.thyme.system.entity.SysRole;
import com.thyme.system.vo.SysMenuVO;
import org.apache.ibatis.annotations.*;
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

    /**
     * 删除菜单
     * @param id id
     * @return 返回值
     */
    @Delete("delete from sys_menu where id = #{id}")
    int deleteMenu(@Param("id") String id);

    /**
     * 修改菜单
     * @param sysMenu 菜单
     * @return 返回值
     */
    @Update("update sys_menu set menu_name = #{menuName}, menu_code = #{menuCode}, menu_href = #{menuHref}, " +
            "menu_level = #{menuLevel}, is_show = #{isShow} where id = #{id}")
    int updateMenu(SysMenuVO sysMenu);

    /**
     * 添加菜单
     * @param sysMenu 菜单
     * @return 返回值
     */
    @Insert("insert into sys_menu (`id`,`menu_name`,`menu_code`,`menu_href`,`menu_level`,`menu_weight`,`is_show`,`create_date`,`create_by`) values" +
            "(#{id}, #{menuName}, #{menuCode}, #{menuHref}, #{menuLevel}, #{menuWeight}, ${isShow}, #{createDate}, #{createBy})")
    int addMenu(SysMenuVO sysMenu);

    /**
     * 查询菜单
     * @param menuName 菜单名称
     * @param menuCode 菜单别名
     * @param menuHref 菜单链接
     * @return 返回值
     */
    @Select("select * from sys_menu where menu_name = #{menuName} or menu_code = #{menuCode} or menu_href = #{menuHref}")
    SysMenu getByName(@Param("menuName")String menuName, @Param("menuCode")String menuCode, @Param("menuHref")String menuHref);


    /**
     * 根据id查询菜单
     * @param id id
     * @return 菜单
     */
    @Select("select * from sys_menu where id = #{id}")
    SysMenu getById(@Param("id")String id);
}
