package com.jason.tics.user.service;

import com.jason.tics.user.domain.User;

/**
 * @author Jason
 */
public interface UserService {
    User register(String email, long uid, String name);
}
