package com.thyme.system.rest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.common.base.ApiResponse;
import com.thyme.common.utils.UUIDUtils;
import com.thyme.system.entity.SysMenu;
import com.thyme.system.entity.SysMenuRole;
import com.thyme.system.entity.SysRole;
import com.thyme.system.service.SysMenuRoleService;
import com.thyme.system.service.SysMenuService;
import com.thyme.system.service.SysRoleService;
import com.thyme.system.vo.MenuListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuiyating
 * @date 2020/1/3 15:45
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleRestController {

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    private final SysMenuRoleService sysMenuRoleService;

    @GetMapping("/getRoleInfo")
    public ApiResponse getRoleInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysRole> sysRoleList = sysRoleService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysRoleList.getTotal());
        jsonObject.put("page",sysRoleList.getCurrent());
        jsonObject.put("page_size",sysRoleList.getSize());
        jsonObject.put("sysRoleList",sysRoleList.getRecords());
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/deleteRole")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse deleteRole(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(id);
            sysRoleService.deleteRole(id);
            jsonObject.put("code",200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/updateRole")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse updateRole(@RequestParam("id")String id,
                                  @RequestParam("name")String name,
                                  @RequestParam("authority")String authority,
                                  @RequestParam("ids[]")String[] ids){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(id);
            for (String menuId : ids){
                SysMenuRole sysMenuRole = new SysMenuRole(menuId, id);
                sysMenuRoleService.addMenuRole(sysMenuRole);
            }
            sysRoleService.updateRole(id, name ,authority);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/addRole")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addRole(@RequestParam("name")String name,
                               @RequestParam("authority")String authority,
                               @RequestParam("ids[]")String[] ids){
        JSONObject jsonObject = new JSONObject();
        try{
            SysRole role = sysRoleService.getByName(name);
            if (role == null){
                String id = UUIDUtils.getSixteenUUID();
                for (String menuId : ids){
                    SysMenuRole sysMenuRole = new SysMenuRole(menuId, id);
                    sysMenuRoleService.addMenuRole(sysMenuRole);
                }
                SysRole sysRole = new SysRole(id, name, authority, new Date());
                sysRoleService.addRole(sysRole);
                jsonObject.put("code",200);
            } else {
                // 501 角色已存在
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getData")
    public ApiResponse getData(){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVo> listVoList = getMenu();
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getRoleMenu")
    public ApiResponse getRoleMenu(@RequestParam("roleId")String roleId){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVo> listVoList = getMenu();
        List<String> roleMenuIds = sysMenuService.getRoleMenu(roleId);
        jsonObject.put("ids", roleMenuIds);
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    private List<MenuListVo> getMenu(){
        List<MenuListVo> listVoList = new LinkedList<>();
        List<SysMenu> firstMenuList = sysMenuService.getFirstMenu();
        //组装数据
        for (SysMenu sysMenu : firstMenuList) {
            List<SysMenu> secondMenu = sysMenuService.findByParentId(sysMenu.getId());
            listVoList.add(MenuListVo.builder().id(sysMenu.getId())
                    .children(secondMenu)
                    .isShow(sysMenu.getIsShow())
                    .menuCode(sysMenu.getMenuCode())
                    .menuHref(sysMenu.getMenuHref())
                    .menuIcon(sysMenu.getMenuIcon())
                    .menuLevel(sysMenu.getMenuLevel())
                    .menuName(sysMenu.getMenuName())
                    .menuWeight(sysMenu.getMenuWeight()).build());
        }
        return listVoList;
    }

}
