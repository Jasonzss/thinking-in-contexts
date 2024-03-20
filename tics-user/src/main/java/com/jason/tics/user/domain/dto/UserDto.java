package com.jason.tics.user.domain.dto;

import com.jason.tics.common.jpa.entity.Dto;
import com.jason.tics.user.domain.Sex;
import com.jason.tics.user.domain.User;
import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 */
@Data
public class UserDto implements Dto<Long, User> {
    private Long uid;
    private String email;
    private String nickname;
    private String avatarUrl;
    /**
     * @see com.jason.tics.user.domain.Sex
     */
    private int sex;
    private Date birthday;
    /**
     * 打招呼，类似个性签名
     */
    private String greet;

    public Sex getSex() {
        return Sex.getSex(this.sex);
    }

    @Override
    public User getSource() {
        return null;
    }
}
