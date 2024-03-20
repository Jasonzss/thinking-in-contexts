package com.jason.tics.auth.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jason
 * @since 2023/09/02 - 14:40
 */
@Data
@Entity
@Table
public class Account implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    @Id
    private Long uid;

    private String email;

    private String password;

    /** 账户未过期 */
    private boolean accountNonExpired = true;

    /** 账户未锁定 */
    private boolean accountNonLocked = true;

    /** 用户凭证未过期 */
    private boolean credentialsNonExpired = true;

    /** 账户可用 */
    private boolean enabled = true;
}
