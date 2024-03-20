package com.jason.tics.user.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jason
 */
@Data
@Table
@Entity
public class User {
    @Id
    private Long uid;
    private String email;
    private String nickname;
    private String avatarUrl;
    @Enumerated
    private Sex sex;
    private Date birthday;
    /**
     * 打招呼，类似个性签名
     */
    private String greet;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
