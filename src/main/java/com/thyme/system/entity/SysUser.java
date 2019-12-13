package com.thyme.system.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thyme
 * @ClassName SysUser
 * @Description TODO
 * @Date 2019/12/12 21:44
 */
@Data
@Builder
public class SysUser implements Serializable {

    static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String password;

    private Date createTime;

}
