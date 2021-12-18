package com.xumaggregatepayments.boot.aliy.params.pcpay.received;

import lombok.Data;

@Data
public class ReceivedPcPayParams {
    private String subject;
    private String outTradeNo;
    private String totalAmount;
    private String returnUrl;
}
