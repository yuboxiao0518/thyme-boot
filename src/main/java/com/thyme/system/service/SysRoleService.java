package com.thyme.system.service;

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
}
