package com.thyme.system.service;

import com.thyme.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author thyme
 * @ClassName UserService
 * @Description TODO
 * @Date 2019/12/11 10:30
 */
public interface SysUserService {

    /**
     * 根据姓名查询
     */
    SysUser findByName(@Param("name") String name);
}
