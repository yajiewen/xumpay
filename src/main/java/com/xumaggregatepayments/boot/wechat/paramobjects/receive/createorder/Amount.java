package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Amount {
    private Integer total; // 总金额(必填) : 订单总金额，单位为分。示例值：100
    private String currency = "CNY"; // 货币类型(非必填) : CNY：人民币，境内商户号仅支持人民币。示例值：CNY
}
