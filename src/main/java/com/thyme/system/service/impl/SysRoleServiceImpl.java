package com.thyme.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.dao.SysRoleDao;
import com.thyme.system.entity.SysRole;
import com.thyme.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author thyme
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @Date 2019/12/13 13:29
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleDao sysRoleDao;

    @Override
    public SysRole findByUserId(String userId) {
        return sysRoleDao.findByUserId(userId);
    }

    @Override
    public IPage<SysRole> getAll(Page page) {
        return sysRoleDao.getAll(page);
    }

    @Override
    public int deleteRole(String id) {
        return sysRoleDao.deleteRole(id);
    }

    @Override
    public int updateRole(String id, String name, String authority) {
        return sysRoleDao.updateRole(id, name, authority);
    }

    @Override
    public int addRole(SysRole sysRole) {
        return sysRoleDao.addRole(sysRole);
    }

    @Override
    public SysRole getByName(String name) {
        return sysRoleDao.getByName(name);
    }
}
