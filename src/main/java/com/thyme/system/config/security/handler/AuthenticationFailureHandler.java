package com.thyme.system.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.thyme.common.base.ApiResponse;
import com.thyme.common.utils.RequestUtils;
import com.thyme.common.utils.ResponseUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author thyme
 * @ClassName AuthenticationFailureHandler
 * @Description TODO
 * @Date 2019/12/11 11:35
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final String LOGIN_FAIL = JSON.toJSONString(ApiResponse.fail("登录失败"));

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            ResponseUtils.print(response, LOGIN_FAIL);
        } else {
            super.onAuthenticationFailure(request, response, e);
        }
    }
}