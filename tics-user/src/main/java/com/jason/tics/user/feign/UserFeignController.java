package com.jason.tics.user.feign;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.user.bo.UserBo;
import com.jason.tics.api.user.feign.UserFeignClient;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class UserFeignController implements UserFeignClient {
    @Autowired
    private UserService userService;

    @Override
    public ServerResponseEntity<UserBo> addUserByEmail(String email, long uid, String name) {
        UserBo bo = BeanUtil.copyProperties(userService.register(email, uid, name), UserBo.class);
        return ServerResponseEntity.success(bo);
    }
}
