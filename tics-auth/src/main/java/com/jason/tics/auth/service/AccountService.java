package com.jason.tics.auth.service;

import com.jason.tics.auth.domain.Account;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public interface AccountService {
    Account registry(String email, String password);

    void modifyPassword(long uid, String oldPwd, String newPwd);
}
