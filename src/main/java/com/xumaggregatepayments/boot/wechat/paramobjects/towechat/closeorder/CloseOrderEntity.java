package com.xumaggregatepayments.boot.wechat.paramobjects.towechat.closeorder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-29 16-09-12
 * @Description 存放关闭订单请求,发送给微信平台的数据
*/
@Data
@AllArgsConstructor
public class CloseOrderEntity {
    private String mchid;
    /*
     * @Description
     * 商户退款单号
     * 必填
     * */
//    private String out_trade_no;
}
