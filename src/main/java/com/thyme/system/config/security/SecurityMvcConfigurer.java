package com.thyme.system.config.security;

import com.thyme.system.config.interceptor.LogHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author thyme
 * @ClassName SecurityMvcConfigurer
 * @Description TODO
 * @Date 2019/12/11 15:41
 */
@AllArgsConstructor
@Configuration
public class SecurityMvcConfigurer implements WebMvcConfigurer {

    private LogHandlerInterceptor logHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册过滤器
        //registry.addInterceptor(logHandlerInterceptor).addPathPatterns("/login");
    }


    /**
     * 页面跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login.html");
    }

}
