package com.jason.tics.auth.controller;

import com.anji.captcha.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;


}
