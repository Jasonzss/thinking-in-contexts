package com.jason.tics.api.auth.feign;

import com.jason.tics.api.auth.bo.AccountBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-auth")
public interface AuthFeignClient {
    @GetMapping("/auth/account/{uid}")
    ServerResponseEntity<AccountBo> getUserAccount(@PathVariable long uid);
}
