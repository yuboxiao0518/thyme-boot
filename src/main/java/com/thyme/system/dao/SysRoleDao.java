package com.thyme.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thyme.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author thyme
 * @ClassName SysRoleDao
 * @Description TODO
 * @Date 2019/12/12 21:48
 */
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {

    @Select("SELECT * FROM sys_role WHERE id =(SELECT role_id FROM sys_user_role WHERE user_id = #{userId})")
    SysRole findByUserId(@Param("userId") String userId);

}
