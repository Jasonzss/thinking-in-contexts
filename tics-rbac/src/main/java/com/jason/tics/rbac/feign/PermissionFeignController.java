package com.jason.tics.rbac.feign;

import com.jason.tics.api.rbac.feign.PermissionFeignClient;
import com.jason.tics.common.core.http.HttpMethod;
import com.jason.tics.common.core.response.ServerResponseEntity;

import java.util.List;

/**
 * @author Jason
 */
public class PermissionFeignController implements PermissionFeignClient {
    @Override
    public ServerResponseEntity<Boolean> checkPermission(long uid, String uri, HttpMethod method) {
        return null;
    }

    @Override
    public ServerResponseEntity<List<String>> listUserPermission() {
        return null;
    }
}
