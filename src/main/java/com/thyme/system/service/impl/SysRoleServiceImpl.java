package com.thyme.system.service.impl;

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
}
