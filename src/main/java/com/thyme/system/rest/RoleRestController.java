package com.thyme.system.rest;
import java.text.ParseException;
import	java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.common.base.ApiResponse;
import com.thyme.common.utils.UUIDUtils;
import com.thyme.system.entity.SysRole;
import com.thyme.system.entity.SysUser;
import com.thyme.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResponse deleteRole(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            if (sysRoleService.deleteRole(id) > 0){
                jsonObject.put("code",200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/updateRole")
    public ApiResponse updateRole(@RequestParam("id")String id,
                                  @RequestParam("name")String name,
                                  @RequestParam("authority")String authority){
        JSONObject jsonObject = new JSONObject();
        try{
            if (sysRoleService.updateRole(id, name, authority) > 0){
                jsonObject.put("code", 200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/addRole")
    public ApiResponse addRole(@RequestParam("name")String name,
                               @RequestParam("authority")String authority){
        JSONObject jsonObject = new JSONObject();
        SysRole role = sysRoleService.getByName(name);
        if (role == null){
            SysRole sysRole = new SysRole(UUIDUtils.getSixteenUUID(),name,authority,new Date());
            if (sysRoleService.addRole(sysRole) > 0){
                jsonObject.put("code",200);
            } else {
                jsonObject.put("code",500);
            }
        } else {
            // 501 角色已存在
            jsonObject.put("code",501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }
}
