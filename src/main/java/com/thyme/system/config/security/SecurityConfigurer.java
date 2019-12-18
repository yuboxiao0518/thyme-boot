package com.thyme.system.config.security;

import com.thyme.system.config.filter.ValidateCodeFilter;
import com.thyme.system.config.security.handler.AuthenticationFailureHandler;
import com.thyme.system.config.security.handler.AuthenticationSuccessHandler;
import com.thyme.system.config.security.handler.CustomLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author thyme
 * @ClassName SecurityConfigurer
 * @Description TODO
 * @Date 2019/12/11 10:47
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;

    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final  ValidateCodeFilter validateCodeFilter;

    private final CustomInvalidSessionStrategy customInvalidSessionStrategy;

    private final CustomExpiredSessionStrategy customExpiredSessionStrategy;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //放行所有的 css和js文件
                .antMatchers("/static/**","/favicon.ico","/actuator/**","/code","/invalid_session","/expired").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler())
                .failureHandler(new AuthenticationFailureHandler())
                .permitAll()
                .and()
                .csrf().disable()
                .cors()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                     // 防止iframe 造成跨域
        .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement()
                    //.invalidSessionUrl("/invalid_session")
                    .invalidSessionStrategy(customInvalidSessionStrategy)  //失效处理
                    .maximumSessions(1)  //同一账号同时允许多个设备在线
                    .maxSessionsPreventsLogin(false)  //新用户挤走前用户
                    .expiredUrl("/expired")
                    .expiredSessionStrategy(customExpiredSessionStrategy) //超时处理
                    .sessionRegistry(sessionRegistry);
    }

    /**
     * 校验用户信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
        //auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SessionRegistry getSessionRegistry(){
        return new SessionRegistryImpl();
    }


}
