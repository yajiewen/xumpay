package com.xumaggregatepayments.boot.aliy.beans;

import com.alipay.easysdk.factory.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yajiewen
 * @Date 2021-11-06 14-50-56
 * @Description 外部类的bean
*/
@Configuration
public class AllReturnAliyBeans {
    @Autowired
    CommonParams commonParams;
    /*
    * @Description
    * 设置支付宝支付factory
    * */
    @Bean
    public Factory getFactory(){
        Factory factory = new Factory();
        factory.setOptions(commonParams.getOptions());
        return factory;
    }

}
