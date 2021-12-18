package com.xumaggregatepayments.boot.wechat.paramobjects.returned.refundorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 12-09-21
 * @Description 保存返回给后端的 退款处理信息
*/
@Data
public class RefunderOrderDealInfo {
    private boolean success = true;
    /*
    * @Description
    * 存放微信返回的数据
    * */
    private String orderInfo;
}
