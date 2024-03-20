package com.jason.tics.auth.feign;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.auth.bo.AccountBo;
import com.jason.tics.api.auth.feign.AuthFeignClient;
import com.jason.tics.auth.repository.AccountRepository;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class AuthFeignController implements AuthFeignClient {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ServerResponseEntity<AccountBo> getUserAccount(long uid) {
        return ServerResponseEntity.success(BeanUtil.copyProperties(accountRepository.getById(uid), AccountBo.class));
    }
}
