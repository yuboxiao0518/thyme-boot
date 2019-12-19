package com.thyme.system.service.impl;

import com.thyme.system.dao.SysMenuDao;
import com.thyme.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author thyme
 * @ClassName SysMenuServiceImpl
 * @Description TODO
 * @Date 2019/12/19 15:43
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuDao sysMenuDao;


}
