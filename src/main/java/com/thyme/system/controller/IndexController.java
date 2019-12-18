package com.thyme.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author thyme
 * @ClassName LoginController
 * @Description TODO
 * @Date 2019/12/11 10:50
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "main";
    }

    @RequestMapping("/main")
    public String main(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "views/home";
    }

    @RequestMapping("/position")
    public String position(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "views/position";
    }

    @RequestMapping("/role")
    public String role(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "views/role";
    }

    @RequestMapping("/menu")
    public String menu(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "views/menu";
    }

    @RequestMapping("/index")
    public String index2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "index";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "是管理员";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String user(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "是普通用户";
    }
}
