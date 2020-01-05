package com.thyme.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author thyme
 * @ClassName SysRoleDao
 * @Description TODO
 * @Date 2019/12/12 21:48
 */
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {

    @Select("SELECT * FROM sys_role WHERE id =(SELECT role_id FROM sys_user_role WHERE user_id = #{userId})")
    SysRole findByUserId(@Param("userId") String userId);


    @Select("SELECT r.* FROM sys_role AS r LEFT JOIN sys_user_role as u ON r.id = u.role_id " +
            "WHERE u.user_id = (SELECT id FROM sys_user WHERE name = #{name})")
    SysRole findByName(@Param("name") String name);

    /**
     * 查询所有角色
     * @param page 分页数据
     * @return 所有角色集合
     */
    @Select("SELECT * FROM sys_role")
    IPage<SysRole> getAll(Page page);

    /**
     * 删除角色
     * @param id id
     * @return 返回值
     */
    @Delete("delete from sys_role where id = #{id}")
    int deleteRole(@Param("id")String id);

    /**
     * 修改角色
     * @param id id
     * @param name 角色名称
     * @param authority 角色权限
     * @return 返回值
     */
    @Update("update sys_role set name = #{name},authority = #{authority} where id = #{id}")
    int updateRole(@Param("id")String id, @Param("name")String name, @Param("authority")String authority);

    /**
     * 添加角色
     * @param sysRole 角色实例
     * @return 返回值
     */
    @Insert("insert into sys_role (`id`,`name`,`authority`,`create_time`) values (#{id},#{name},#{authority},#{createTime})")
    int addRole(SysRole sysRole);

    /**
     * 根据名称查角色
     * @param name 名称
     * @return 角色
     */
    @Select("select * from sys_role where name = #{name}")
    SysRole getByName(@Param("name")String name);
}
