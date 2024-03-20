package com.jason.tics.auth.controller;

import com.jason.tics.auth.domain.Account;
import com.jason.tics.auth.service.AccountService;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ServerResponseEntity<Account> registryAccount(){
        return ServerResponseEntity.success(accountService.registry(null, null));
    }

    @PutMapping("/{uid}/password")
    public ServerResponseEntity<Account> modifyPassword(@RequestParam String oldPwd, @RequestParam String newPwd,
                                                        @PathVariable long uid){
        return null;
    }
}
