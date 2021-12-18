package com.xumaggregatepayments.boot.aliy.params.mobilepay.received;

import lombok.Data;
import lombok.ToString;

/**
 * @Author yajiewen
 * @Date 2021-11-08 11-48-43
 * @Description 手机支付
*/
@Data
@ToString
public class ReceivedMobilePayParams {
    private String subject;
    private String outTradeNo;
    private String totalAmount;
    private String quitUrl;  // 用户付款中途退出返回商户网站的地址
    private String returnUrl; // 支付成功后同步跳转的页面，是一个http/https开头的字符串 非必填
}
