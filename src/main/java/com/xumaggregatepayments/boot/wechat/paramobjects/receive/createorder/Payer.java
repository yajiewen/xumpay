package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;

@Data
public class Payer {
    private String openid; // 用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid
}
