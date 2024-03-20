package com.jason.tics.rbac.domain;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class MenuPermission {
    private Long menuId;
    private Long permissionId;

    /**
     * 权限对应的编码
     */
    private String permission;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源对应服务器路径
     */
    private String uri;
}
