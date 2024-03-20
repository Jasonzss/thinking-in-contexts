package com.jason.tics.user.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.user.domain.User;
import com.jason.tics.user.domain.dto.UserDto;
import com.jason.tics.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{uid}")
    public ServerResponseEntity<User> getUser(@PathVariable long uid){
        return ServerResponseEntity.success(userRepository.getById(uid));
    }

    @PutMapping("/{uid}")
    public ServerResponseEntity<User> update(@RequestBody @Validated UserDto userDto){
        return ServerResponseEntity.success(userRepository.updateByPojo(userDto.getUid(), userDto));
    }
}
