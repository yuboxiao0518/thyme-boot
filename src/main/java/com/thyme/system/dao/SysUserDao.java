package com.thyme.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thyme
 * @ClassName SysUserDao
 * @Description TODO
 * @Date 2019/12/12 21:50
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 根据姓名查询
     * @param name 姓名
     * @return 用户实例
     */
    @Select("SELECT * FROM sys_user WHERE name= #{name}")
    SysUser findByName(@Param("name") String name);

    /**
     * 查询所有用户
     * @param page 分页数据
     * @return 所有用户集合
     */
    @Select("SELECT * FROM sys_user")
    IPage<SysUser> getAll(Page page);

    /**
     * 删除用户
     * @param id id
     * @return 返回值
     */
    @Delete("delete from sys_user where id = #{id}")
    int deleteRole(@Param("id")String id);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户集合
     */
    @Select("select * from sys_user where id = #{id}")
    SysUser getById(@Param("id")String id);

    /**
     * 修改用户
     * @param sysUser 用户
     * @return 返回值
     */
    @Update("update sys_user set name = #{name}, password = #{password}, nickName = #{nickName}, sex = #{sex}, mobile = #{mobile}, email = #{email}, " +
            "birthday = #{birthday}, hobby = #{hobby}, live_address = #{liveAddress}, update_time = #{updateTime} where id = #{id}")
    int updateUser(SysUser sysUser);

    /**
     * 添加用户
     * @param sysUser 用户
     * @return 返回值
     */
    @Insert("insert into sys_user (`id`, `name`, `password`, `nickname`, `sex`, `mobile`, `email`, `birthday`, `hobby`, `live_address`, `create_time`) values" +
            "(#{id}, #{name}, #{password}, #{nickName}, #{sex}, #{mobile}, #{email}, #{birthday}, #{hobby}, #{liveAddress}, #{createTime})")
    int addUser(SysUser sysUser);

    /**
     * 根据名称查询用户
     * @param name 名称
     * @return 用户
     */
    @Select("select * from sys_user where name = #{name}")
    SysUser getByName(String name);

}
