package com.xumaggregatepayments.boot.wechat.interceptors;

import com.xumaggregatepayments.boot.globalConfig.InputStreamHelper.RequestWrapper;
import com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder.OrderReceivedRequestParameters;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.ParamsDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.RequestParamsDealInfoForInterceptor;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.JsonHelper;

import com.xumaggregatepayments.boot.wechat.service.paramsoperation.ParamsValidater;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.PaymentMethodUrlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author yajiewen
 * @Date 2021-10-27 13-55-27
 * @Description
*/
@Component
@Slf4j
public class InterceptorsForParams implements HandlerInterceptor {
    @Autowired
    JsonHelper jsonHelper;
    @Autowired
    ParamsValidater paramsValidater;
    @Autowired
    PaymentMethodUrlMapper paymentMethodUrlMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("前置拦截方法执行");
        String body = new RequestWrapper(request).getBodyString(); // 获取请求body中的json
        OrderReceivedRequestParameters orderReceivedRequestParameters = jsonHelper.jsonToObject(body, OrderReceivedRequestParameters.class);

        // 判断传入支付方法参数是否存在
        String requestURI = request.getRequestURI();
        String[]  requestURIPart = requestURI.split("/");
        String paymentMethod = requestURIPart[requestURIPart.length-1];
        String paymentUrl = paymentMethodUrlMapper.paymentMethodUrlGetter(paymentMethod);
        if(paymentUrl == null){
            // 创建保存返回信息的对象
            RequestParamsDealInfoForInterceptor requestParamsDealInfoForInterceptor = new RequestParamsDealInfoForInterceptor(new ParamsDealInfo());
            requestParamsDealInfoForInterceptor.getParamsDealInfo().setPaymentMethodError(true);
            response.getOutputStream().print(jsonHelper.objectToJson(requestParamsDealInfoForInterceptor));
            return false;
        }

        // 判断传入body参数是否合规
        if(!paramsValidater.validate(orderReceivedRequestParameters,paymentMethod)){
            // 创建保存返回信息的对象
            RequestParamsDealInfoForInterceptor requestParamsDealInfoForInterceptor = new RequestParamsDealInfoForInterceptor(new ParamsDealInfo());
            requestParamsDealInfoForInterceptor.getParamsDealInfo().setParamsError(true);
            response.getOutputStream().print(jsonHelper.objectToJson(requestParamsDealInfoForInterceptor));
            return false;
        }
        System.out.println("前置方法结束");
        return true;
    }
}
