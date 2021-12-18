package com.xumaggregatepayments.boot.aliy.params.apppay.received;

import lombok.Data;
/**
 * @Author yajiewen
 * @Date 2021-11-09 15-50-49
 * @Description app 支付参数
*/
@Data
public class ReceivedAppPayParams {
    private String subject;
    private String outTradeNo;
    private String totalAmount;
}
