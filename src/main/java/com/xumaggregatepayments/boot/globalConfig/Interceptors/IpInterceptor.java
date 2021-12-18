package com.xumaggregatepayments.boot.globalConfig.Interceptors;

import com.xumaggregatepayments.boot.globalConfig.beans.AllowIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yajiewen
 * @Date 2021-11-16 11-55-22
 * @Description ip 白名单拦截器 仅允许本机访问
*/

@Component
public class IpInterceptor implements HandlerInterceptor {
    @Autowired
    AllowIp allowIp;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteIp = request.getRemoteAddr();

        if(allowIp.getIp().contains(remoteIp)){
            return true;
        }else{
            return false;
        }
    }
}
