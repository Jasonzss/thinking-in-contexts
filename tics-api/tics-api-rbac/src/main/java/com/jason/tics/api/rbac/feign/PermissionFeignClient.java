package com.jason.tics.api.rbac.feign;

import com.jason.tics.common.core.http.HttpMethod;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-rbac")
public interface PermissionFeignClient {
    @GetMapping("")
    ServerResponseEntity<Boolean> checkPermission(@RequestParam("userId") long uid,
                                                  @RequestParam("uri") String uri,
                                                  @RequestParam("method") HttpMethod method);

    ServerResponseEntity<List<String>> listUserPermission();
}
