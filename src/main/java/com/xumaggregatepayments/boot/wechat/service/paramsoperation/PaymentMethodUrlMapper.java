package com.xumaggregatepayments.boot.wechat.service.paramsoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-20
 * @Description
*/ 
@Service
@ConfigurationProperties(prefix = "weichatpay")
public class PaymentMethodUrlMapper {
    @Autowired
    WechatPayRequestParametersAutoSetter wechatPayRequestParametersAutoSetter;
    public String paymentMethodUrlGetter(String paymentMethod){
        return wechatPayRequestParametersAutoSetter.getPaymentUrls().get(paymentMethod);
    }
}
