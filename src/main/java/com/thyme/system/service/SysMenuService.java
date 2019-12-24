package com.thyme.system.service;

import com.thyme.system.entity.SysMenu;
import com.thyme.system.vo.MenuVo;

import java.util.List;

/**
 * @author thyme
 * @ClassName SysMenuService
 * @Description TODO
 * @Date 2019/12/19 15:43
 */
public interface SysMenuService {

    public List<MenuVo> getMenuByUser(String username);
}
