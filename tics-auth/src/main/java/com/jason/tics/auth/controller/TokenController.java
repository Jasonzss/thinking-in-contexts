package com.jason.tics.auth.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
public class TokenController {
    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/token/{uid}")
    public ServerResponseEntity<Void> get(@RequestBody CaptchaVO captchaVO) {
        //必传参数：captchaVO.captchaVerification
        ResponseModel response = captchaService.verification(captchaVO);
        if(!response.isSuccess()){
            return ServerResponseEntity.fail(ExceptionResponseEnum.SHOW_FAIL
                    .setMsg("验证失败["+response.getRepCode()+"]："+response.getRepMsg()));
        }
        return null;
    }
}
