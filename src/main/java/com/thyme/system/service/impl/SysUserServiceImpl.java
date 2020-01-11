package com.thyme.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.dao.SysUserDao;
import com.thyme.system.entity.SysUser;
import com.thyme.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author thyme
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2019/12/11 10:30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao;

    @Override
    public SysUser findByName(String name) {
        return sysUserDao.findByName(name);
    }

    @Override
    public IPage<SysUser> getAll(Page page) {
        return sysUserDao.getAll(page);
    }

    @Override
    public SysUser getById(String id) {
        return sysUserDao.getById(id);
    }

    @Override
    public SysUser getByName(String name) {
        return sysUserDao.getByName(name);
    }
}
