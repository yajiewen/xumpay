package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Settle_info {
    private boolean profit_sharing; // 是否指定分账(非必填) : 是否指定分账 示例值：false
}
