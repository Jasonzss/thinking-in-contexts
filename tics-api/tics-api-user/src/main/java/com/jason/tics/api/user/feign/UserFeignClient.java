package com.jason.tics.api.user.feign;

import com.jason.tics.api.user.bo.UserBo;
import com.jason.tics.common.core.response.ServerResponseEntity;

/**
 * @author Jason
 */
public interface UserFeignClient {
    ServerResponseEntity<UserBo> addUserByEmail(String email, long uid, String name);
}
