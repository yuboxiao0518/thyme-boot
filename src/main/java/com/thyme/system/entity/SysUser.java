package com.thyme.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thyme
 * @ClassName SysUser
 * @Description TODO
 * @Date 2019/12/12 21:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUser extends Model<SysUser> {

    static final long serialVersionUID = 1L;

    @TableId
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

    private Date createTime;

    private Date updateTime;

}
