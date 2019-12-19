package com.thyme.system.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author thyme
 * @ClassName SysMenu
 * @Description TODO
 * @Date 2019/12/19 15:37
 */
@Data
@Builder
public class SysMenu implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * 菜单主键
     */
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
    private String createDate;

    /**
     * 菜单创建人
     */
    private String createBy;

}
