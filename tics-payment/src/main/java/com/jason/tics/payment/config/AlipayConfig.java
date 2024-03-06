package com.jason.tics.payment.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jason
 */
@Configuration
public class AlipayConfig {
    @Value("${alipay.serverUrl}")
    private String serverUrl;
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.privateKey}")
    private String privateKey;
    @Value("${alipay.format}")
    private String format;
    @Value("${alipay.charset}")
    private String charset;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;
    @Value("${alipay.signType}")
    private String signType;
    @Value("${alipay.encryptKey}")
    private String encryptKey;
    @Value("${alipay.encryptType}")
    private String encryptType;


    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(serverUrl, appId, privateKey, format,
                charset, alipayPublicKey, signType, encryptKey, encryptType);
    }
}
