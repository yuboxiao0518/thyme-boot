package com.thyme.system.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author thyme
 * @ClassName SecurityMvcConfigurer
 * @Description TODO
 * @Date 2019/12/11 15:41
 */
@Configuration
public class SecurityMvcConfigurer implements WebMvcConfigurer {

    /**
     * 页面跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login.html");
    }

}
