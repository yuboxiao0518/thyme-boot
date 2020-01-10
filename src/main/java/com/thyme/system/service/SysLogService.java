package com.thyme.system.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author thyme
 * @ClassName SysLogService
 * @Description TODO
 * @Date 2020/1/9 16:23
 */
public interface SysLogService {

    int saveLoginLog(HttpServletRequest request,String message,String name);
}
