package com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder;

import lombok.Data;
/**
 * @Author yajiewen
 * @Date 2021-10-27 14-50-27
 * @Description 作为请求参数处理信息的返回对象
*/ 
@Data
public class ParamsDealInfo {
    private boolean isParamsError = false;
    private boolean isPaymentMethodError = false;
}
