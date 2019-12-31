package com.thyme.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysMenu;
import com.thyme.system.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author thyme
 * @ClassName SysMenuService
 * @Description TODO
 * @Date 2019/12/19 15:43
 */
public interface SysMenuService {

    public List<MenuVo> getMenuByUser(String username);

    IPage<SysMenu> findFirstMenu(Page page);

    List<SysMenu> findByParentId(String parentId);
}
