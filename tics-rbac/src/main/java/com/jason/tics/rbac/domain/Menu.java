package com.jason.tics.rbac.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @author Jason
 */
@Data
public class Menu {
    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 权限，需要有哪个权限才能访问该菜单
     */
    private String permission;

    /**
     * 组件如：1.'Layout' 为布局，不会跳页面 2.'components-demo/tinymce' 跳转到该页面
     */
    private String component;

    /**
     * 设定菜单的名字
     */
    private String name;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 排序，越小越靠前
     */
    private Integer seq;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}
