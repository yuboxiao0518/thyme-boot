package com.thyme.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author thyme
 * @ClassName UserService
 * @Description TODO
 * @Date 2019/12/11 10:30
 */
public interface SysUserService {

    /**
     * 根据姓名查询
     * @param name 姓名
     * @return 用户实例
     */
    SysUser findByName(@Param("name") String name);

    /**
     * 查询所有用户
     * @param page 分页数据
     * @return 所有用户集合
     */
    IPage<SysUser> getAll(Page page);

    /**
     * 删除用户
     * @param id id
     * @return 返回值
     */
    int deleteUser(String id);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户集合
     */
    SysUser getById(String id);

    /**
     * 修改用户
     * @param sysUser 用户
     * @return 返回值
     */
    int updateUser(SysUser sysUser);

    /**
     * 添加用户
     * @param sysUser 用户
     * @return 返回值
     */
    int addUser(SysUser sysUser);

    /**
     * 根据名称查询用户
     * @param name 名称
     * @return 用户
     */
    SysUser getByName(String name);
}
