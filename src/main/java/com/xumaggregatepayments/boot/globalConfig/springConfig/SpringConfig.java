package com.xumaggregatepayments.boot.globalConfig.springConfig;

import com.xumaggregatepayments.boot.globalConfig.InputStreamHelper.ReplaceStreamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-56-45
 * @Description
*/
@Configuration
public class SpringConfig {
    /*注入向spring-boot我们自己的过滤器,解决request输入流只能读取一次的问题*/
    @Bean
    public FilterRegistrationBean replaceStreamFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ReplaceStreamFilter());
        return registrationBean;
    }
}
