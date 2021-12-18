package com.xumaggregatepayments.boot.wechat.paramobjects.returned.queryrefundorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 12-46-27
 * @Description 保存 查询到的退款信息
*/
@Data
public class QueryRefundOrderDealInfo {
    private boolean success;
    private String orderInfo;
}
