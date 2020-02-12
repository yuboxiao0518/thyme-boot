package com.thyme.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thyme
 * @ClassName SysLog
 * @Description TODO
 * @Date 2020/1/9 16:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysLog extends Model<SysLog> {

    static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private String id;

    /**
     * 关联用户
     */
    private String username;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 日志信息
     */
    private String message;

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 设备名称
     */
    private String systemName;

    /**
     * 创建时间
     */
    private Date createDate;
}
