package com.jason.tics.auth.service;

import com.jason.tics.auth.model.AuthAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 查询用户的详细信息用于后续的验权
 *
 * @author Jason
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthAccount account = new AuthAccount();
        account.setUserName(username);
        account.setPassword(this.passwordEncoder.encode("123456"));

        return new User(username, account.getPassword(), account.isEnabled(),
                account.isAccountNonExpired(), account.isCredentialsNonExpired(),
                account.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
