package com.xumaggregatepayments.boot.globalConfig.Interceptors;

import com.xumaggregatepayments.boot.wechat.interceptors.InterceptorsForParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-20
 * @Description
*/ 
@Configuration(proxyBeanMethods = false)
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    InterceptorsForParams interceptorsForParams;
    @Autowired
    IpInterceptor ipInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorsForParams).addPathPatterns("/orderparams/**");
        registry.addInterceptor(ipInterceptor).addPathPatterns("/**");
    }
}
