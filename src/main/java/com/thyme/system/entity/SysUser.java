package com.thyme.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String nickName;

    private String sex;

    private String mobile;

    private String email;

    private String birthday;

    private String hobby;

    private String liveAddress;

    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public SysUser(){

    }

    public SysUser(String id, String name, String password, String nickName, String sex, String mobile,
                   String email, String birthday, String hobby, String liveAddress, Date createTime, Date updateTime){
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.birthday = birthday;
        this.hobby = hobby;
        this.liveAddress = liveAddress;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}
