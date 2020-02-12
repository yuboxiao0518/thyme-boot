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
 * @ClassName SysMenu
 * @Description TODO
 * @Date 2019/12/19 15:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends Model<SysMenu> {

    static final long serialVersionUID = 1L;

    /**
     * 菜单主键
     */
    @TableId
    private String id;

    /**
     * 菜单父级
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单别名
     */
    private String menuCode;

    /**
     * 菜单链接
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单权重
     */
    private String menuWeight;

    /**
     * 菜单是否显示
     */
    private Boolean isShow;

    /**
     * 菜单创建时间
     */
    private Date createDate;

    /**
     * 菜单创建人
     */
    private String createBy;

}
