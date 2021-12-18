package com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 13-42-25
 * @Description 查询订单信息中的amount
*/
@Data
public class Amount {
    private Integer total;
    private Integer payer_total;
    private String currency;
    private String payer_currency;
}
