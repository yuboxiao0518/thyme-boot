package com.thyme.system.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thyme.common.base.ApiResponse;
import com.thyme.common.base.Constants;
import com.thyme.common.utils.UUIDUtils;
import com.thyme.system.entity.SysUser;
import com.thyme.system.entity.SysUserRole;
import com.thyme.system.service.SysRoleService;
import com.thyme.system.service.SysUserRoleService;
import com.thyme.system.service.SysUserService;
import com.thyme.system.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cuiyating
 * @date 2020/1/2 20:45
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRestController {

    private final SysUserService userService;

    private final SysRoleService sysRoleService;

    private final SysUserRoleService sysUserRoleService;

    @GetMapping("/getUserInfo")
    public ApiResponse getUserInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        List<UserVO> userList = new ArrayList<>(16);
        IPage<SysUser> sysUserList = userService.getAll(new Page(page, pageSize));
        if (sysUserList.getRecords() != null && sysUserList.getRecords().size() > 0){
            for (SysUser sysUser : sysUserList.getRecords()){
                //根据用户id查询角色名称
                String roleName = sysRoleService.getById(sysUser.getId());
                UserVO userVO = new UserVO(roleName);
                userVO.setId(sysUser.getId());
                userVO.setName(sysUser.getName());
                userVO.setNickName(sysUser.getNickName());
                userVO.setSex(sysUser.getSex());
                userVO.setMobile(sysUser.getMobile());
                userVO.setEmail(sysUser.getEmail());
                userList.add(userVO);
            }
        }
        jsonObject.put("total",sysUserList.getTotal());
        jsonObject.put("page",sysUserList.getCurrent());
        jsonObject.put("page_size",sysUserList.getSize());
        jsonObject.put("sysUserList",userList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/deleteUser")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse deleteUser(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            sysUserRoleService.deleteByUserId(id);
            userService.deleteById(id);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/updateUser")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse updateRole(@RequestParam("id")String id,
                                  @RequestParam("name")String name,
                                  @RequestParam("nickName")String nickName,
                                  @RequestParam("sex")String sex,
                                  @RequestParam("userRole")String userRole,
                                  @RequestParam("mobile")String mobile,
                                  @RequestParam("email")String email,
                                  @RequestParam("birthday")String birthday,
                                  @RequestParam("hobby")String hobby,
                                  @RequestParam("liveAddress")String liveAddress){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser(id, name, null, nickName, sex, mobile, email, birthday, hobby, liveAddress, null, new Date());
        try {
            sysUserRoleService.deleteByUserId(id);
            sysUserRoleService.insert(new SysUserRole(id,sysRoleService.getIdByName(userRole)));
            userService.updateById(sysUser);
            jsonObject.put("code",200);
        } catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/addUser")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addRole(@RequestParam("name")String name,
                               @RequestParam("password")String password,
                               @RequestParam("nickName")String nickName,
                               @RequestParam("sex")String sex,
                               @RequestParam("userRole")String userRole,
                               @RequestParam("mobile")String mobile,
                               @RequestParam("email")String email,
                               @RequestParam("birthday")String birthday,
                               @RequestParam("hobby")String hobby,
                               @RequestParam("liveAddress")String liveAddress){
        JSONObject jsonObject = new JSONObject();
        SysUser user = userService.getByName(name);
        if (user == null){
            //用户id
            String userId = UUIDUtils.getUUID();
            //角色id
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysRoleService.getIdByName(userRole));
            sysUserRole.setUserId(userId);
            sysUserRoleService.insert(sysUserRole);
            SysUser sysUser = new SysUser(userId, name, new BCryptPasswordEncoder().encode(password), nickName, sex, mobile, email, birthday, hobby, liveAddress, new Date(), null);
            if (userService.insert(sysUser) > 0){
                jsonObject.put("code", 200);
            } else {
                jsonObject.put("code", 500);
            }
        } else {
            // 501 用户已存在
            jsonObject.put("code", 501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/editPassword")
    public ApiResponse editPassword(String id){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(new BCryptPasswordEncoder().encode(Constants.CZMM));
        try{
            if (userService.updateById(sysUser) > 0){
                jsonObject.put("code", 200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getAllRoleName")
    public ApiResponse getAllRoleName(){
        JSONObject jsonObject = new JSONObject();
        List<String> allRoleName = sysRoleService.getAllRoleName();
        jsonObject.put("allRoleName", allRoleName);
        return ApiResponse.ofSuccess(jsonObject);
    }

}
