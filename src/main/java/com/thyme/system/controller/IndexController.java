package com.thyme.system.controller;

import cn.hutool.core.util.IdUtil;
import com.thyme.system.vo.ImgResult;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author thyme
 * @ClassName LoginController
 * @Description TODO
 * @Date 2019/12/11 10:50
 */
@Controller
public class IndexController {

    @GetMapping("/code")
    @ResponseBody
    public ImgResult getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果：5
        String result = captcha.text();
        String uuid = IdUtil.simpleUUID();
        return new ImgResult(captcha.toBase64(),uuid);
    }

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
