package com.jason.tics.common.security.service;

import com.jason.tics.api.auth.bo.AccountBo;
import com.jason.tics.api.auth.feign.AuthFeignClient;
import com.jason.tics.api.rbac.feign.PermissionFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason
 */
@Service
public class TicsUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthFeignClient authFeignClient;
    @Autowired
    private PermissionFeignClient permissionFeignClient;

    /**
     * SpringSecurity 中的 Username 对应 TicsAccount 中的 Uid
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountBo account = authFeignClient.getUserAccount(Long.parseLong(username)).getData();
        List<String> permission = permissionFeignClient.listUserPermission().getData();
        return new User(String.valueOf(account.getUid()), account.getPassword(), account.isEnabled(),
                account.isAccountNonExpired(), account.isCredentialsNonExpired(), account.isAccountNonLocked(),
                AuthorityUtils.createAuthorityList(permission.toArray(new String[0])));
    }
}
