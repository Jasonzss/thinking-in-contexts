package com.jason.tics.rbac.domain;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class UserRole {
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 角色ID
     */
    private Long roleId;
}
