package com.xumaggregatepayments.boot.wechat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.WechatPayRequestParametersAutoSetter;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-56-49
 * @Description
*/
@Configuration
public class AllReturnWechatBeans {
    @Autowired
    WechatPayRequestParametersAutoSetter wechatPayRequestParametersAutoSetter;


    /*构造httpclient 用于发送请求*/
    @Bean
    public HttpClient httpClientGeter(){
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(wechatPayRequestParametersAutoSetter.getMchid(),
                        new PrivateKeySigner(wechatPayRequestParametersAutoSetter.getMerchant_serial_number(),
                                wechatPayRequestParametersAutoSetter.getMerchant_private_key())),
                                wechatPayRequestParametersAutoSetter.getApi_v3_key().getBytes(StandardCharsets.UTF_8));


        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(wechatPayRequestParametersAutoSetter.getMchid(),
                              wechatPayRequestParametersAutoSetter.getMerchant_serial_number(),
                              wechatPayRequestParametersAutoSetter.getMerchant_private_key())
                .withValidator(new WechatPay2Validator(verifier));

        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签，并进行证书自动更新
        HttpClient httpClient = builder.build();
        return httpClient;
    }

    /*构造ObjectMapper*/
    @Bean
    public ObjectMapper objectMapperGeter(){
        return new ObjectMapper();
    }

}
