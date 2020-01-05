package com.thyme.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.system.entity.SysMenu;
import com.thyme.system.vo.MenuVo;
import com.thyme.system.vo.SysMenuVO;
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

    /**
     * 删除菜单
     * @param id id
     * @return 返回值
     */
    int deleteMenu(String id);

    /**
     * 修改菜单
     * @param sysMenu 菜单
     * @return 返回值
     */
    int updateMenu(SysMenuVO sysMenu);

    /**
     * 添加菜单
     * @param sysMenu 菜单
     * @return 返回值
     */
    int addMenu(SysMenuVO sysMenu);

    /**
     * 查询菜单
     * @param menuName 菜单名称
     * @param menuCode 菜单别名
     * @param menuHref 菜单链接
     * @return 返回值
     */
    SysMenu getByName(String menuName, String menuCode, String menuHref);

    /**
     * 根据id查询菜单
     * @param id id
     * @return 菜单
     */
    SysMenu getById(String id);
}
