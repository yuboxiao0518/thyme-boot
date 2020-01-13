package com.thyme.common.base;

/**
 * @author thyme
 * @ClassName Constant
 * @Description TODO
 * @Date 2019/12/19 15:48
 */
public class Constants {

    public static final String LOGIN_URL = "/login";

    public static final String LOGOUT_URL = "/logout";

    public static final Integer INT_PAGE_ERROR = 500;

    public static final Integer INT_PAGE_NOT_FOUND = 404;

    public static final String STRING_PAGE_ERROR = "500";

    public static final String STRING_PAGE_NOT_FOUND = "404";

    public static final String REQUEST_MODE_POST = "post";

    public static final String LOGIN_SUCCESS = "登录成功";

    public static final String LOGIN_FAIL = "登录失败";

    public static final String LOGOUT_SUCCESS = "退出成功";

    public static final String LOGIN_MAX_LIMIT = "登录超出最大限制";

    /**
     * 五分钟需要之内允许修改密码错误三次
     */
    public static final Long PASSWORD_UPDATE_MINUTE = 300L;

    public static final String PASSWORD_UPDATE = "PASSWORD_UPDATE";


    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";

    public static final String INTRANET_IP = "内网IP";

    /**
     * 重置密码  123456
     */
    public static final String CZMM = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
}
