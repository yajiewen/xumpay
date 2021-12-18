package com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 13-57-41
 * @Description 返回给前端的查询到的订单信息
*/
@Data
public class QueryOrderDealInfo {
    private boolean success = true;
    private String otherInfo;
    private QueryOrderInfo orderInfo;
}
