package com.jason.tics.auth.service.impl;

import com.jason.tics.auth.domain.Account;
import com.jason.tics.auth.repository.AccountRepository;
import com.jason.tics.auth.service.AccountService;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account registry(String email, String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        accountRepository.save(account);
        return account;
    }

    @Override
    public void modifyPassword(long uid, String oldPwd, String newPwd) {
        Account account = accountRepository.getById(uid);
        if (account.getPassword().equals(oldPwd)){
            account.setPassword(newPwd);
        }else {
            throw new TicsException(ExceptionResponseEnum.SHOW_FAIL.setMsg("错误的旧密码"));
        }

        accountRepository.save(account);
    }
}
