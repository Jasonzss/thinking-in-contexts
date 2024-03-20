package com.jason.tics.api.auth.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class AccountBo implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    private Long uid;

    private String email;

    private String password;

    /** 账户未过期 */
    private boolean accountNonExpired;

    /** 账户未锁定 */
    private boolean accountNonLocked;

    /** 用户凭证未过期 */
    private boolean credentialsNonExpired;

    /** 账户可用 */
    private boolean enabled;
}
