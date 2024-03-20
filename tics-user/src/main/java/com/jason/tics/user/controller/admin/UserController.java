package com.jason.tics.user.controller.admin;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.user.domain.User;
import com.jason.tics.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("/admin/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ServerResponseEntity<Page<User>> listUsers(Pageable pageable){
        return ServerResponseEntity.success(userRepository.findAll(pageable));
    }
}
