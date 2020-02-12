package com.thyme.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thyme.system.dao.SysUserRoleDao;
import com.thyme.system.entity.SysUserRole;
import com.thyme.system.service.SysUserRoleService;
import com.thyme.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuiyating
 * @date 2020/1/12 0:45
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao,SysUserRole> implements SysUserRoleService {

    @Override
    public int insert(SysUserRole sysUserRole) {
        return baseMapper.insert(sysUserRole);
    }

    @Override
    public int deleteByUserId(String userId) {
        return baseMapper.deleteByUserId(userId);
    }
}
