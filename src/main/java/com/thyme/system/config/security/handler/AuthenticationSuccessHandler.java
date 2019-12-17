package com.thyme.system.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.thyme.common.base.ApiResponse;
import com.thyme.common.utils.RequestUtils;
import com.thyme.common.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author thyme
 * @ClassName AuthenticationSuccessHandler
 * @Description TODO
 * @Date 2019/12/11 10:56
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private static final String LOGIN_SUCCESS = JSON.toJSONString(ApiResponse.success("登录成功"));

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            ResponseUtils.print(response, LOGIN_SUCCESS);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }


}
