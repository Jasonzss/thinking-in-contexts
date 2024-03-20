package com.jason.tics.user.service.impl;

import com.jason.tics.user.domain.Sex;
import com.jason.tics.user.domain.User;
import com.jason.tics.user.repository.UserRepository;
import com.jason.tics.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public static final String DEFAULT_GREET = "hi there!";
    public static final String DEFAULT_AVATAR_URL = "https://avatars.githubusercontent.com/u/78007805?v=4";

    @Override
    public User register(String email, long uid, String name) {
        User user = new User();
        user.setEmail(email);
        user.setUid(uid);
        user.setNickname(name);
        user.setGreet(DEFAULT_GREET);
        user.setAvatarUrl(DEFAULT_AVATAR_URL);
        user.setSex(Sex.SECRET);
        return userRepository.save(user);
    }
}
