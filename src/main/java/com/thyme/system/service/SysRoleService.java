package com.thyme.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author thyme
 * @ClassName SysRoleService
 * @Description TODO
 * @Date 2019/12/13 13:28
 */
public interface SysRoleService {

    SysRole findByUserId(@Param("userId") String userId);

    /**
     * 查询所有角色
     * @param page 分页数据
     * @return 所有角色集合
     */
    IPage<SysRole> getAll(Page page);

    /**
     * 删除角色
     * @param id id
     * @return 返回值
     */
    int deleteRole(String id);

    /**
     * 修改角色
     * @param id id
     * @param name 角色名称
     * @param authority 角色权限
     * @return 返回值
     */
    int updateRole(String id, String name, String authority);

    /**
     * 添加角色
     * @param sysRole 角色实例
     * @return 返回值
     */
    int addRole(SysRole sysRole);

    /**
     * 根据名称查角色
     * @param name 名称
     * @return 角色
     */
    SysRole getByName(String name);
}
