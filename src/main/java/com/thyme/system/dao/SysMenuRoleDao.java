package com.thyme.system.dao;

import com.thyme.system.entity.SysMenuRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cuiyating
 * @date 2020/1/8 14:23
 */
@Repository
public interface SysMenuRoleDao {

    /**
     * 添加角色和菜单的联系
     * @param sysMenuRole 角色和菜单的实例
     * @return 返回值
     */
    @Insert("insert into sys_menu_role (`menu_id`, `role_id`) values (#{menuId}, #{roleId})")
    int addMenuRole(SysMenuRole sysMenuRole);

    /**
     * 根据角色id删除对应的角色和菜单的联系
     * @param roleId 角色id
     * @return 返回值
     */
    @Delete("delete from sys_menu_role where role_id = #{roleId}")
    int deleteByRoleId(@Param("roleId")String roleId);

    /**
     * 根据角色id查询所有菜单id
     * @param roleId 角色id
     * @param parentIds 菜单id
     * @return 所有菜单id
     */
   /* @Select({
            "<script>",
            "select menu_id from sys_menu_role",
            "where role_id = #{roleId} and menu_id not in",
            "<foreach collection='parentIds' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })*/
   @Select("select menu_id from sys_menu_role where role_id = #{roleId} and menu_id not in (#{parentIds})")
    List<String> getAllMenuId(@Param("roleId")String roleId, @Param("parentIds")List<String> parentIds);
}
