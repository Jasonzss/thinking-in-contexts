package com.jason.tics.rbac.domain;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class Role {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;
}
