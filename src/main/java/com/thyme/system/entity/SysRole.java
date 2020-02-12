package com.thyme.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thyme
 * @ClassName SysRole
 * @Description TODO
 * @Date 2019/12/12 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends Model<SysRole> {

    static final long serialVersionUID = 1L;

    private String id;

    private String authority;

    private String name;

    private Date createTime;


}
