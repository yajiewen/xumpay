package com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-19
 * @Description 用于保存拦截器的参数处理信息
*/ 
@Data
@AllArgsConstructor
public class RequestParamsDealInfoForInterceptor {
    ParamsDealInfo paramsDealInfo;
}
