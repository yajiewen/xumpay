package com.xumaggregatepayments.boot.aliy.params.mobilepay.returned;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-11-08 11-52-40
 * @Description 返回手机支付结果
*/
@Data
public class ReturnedMobilePayMessage {

    private String orderInfo;
    private boolean success;
}
