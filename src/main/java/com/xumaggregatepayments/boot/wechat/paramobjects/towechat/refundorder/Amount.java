package com.xumaggregatepayments.boot.wechat.paramobjects.towechat.refundorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 11-42-07
 * @Description 退款金额信息
*/
@Data
public class Amount {
    /*
    * @Description
    * 退款金额 整数 单位为分
    * 必填
    * */
    private Integer refund;

    /*
    * @Description
    * 退款出资账户及金额
    * 非必填
    * */
    private From[] from;

    /*
     * @Description
     *原订单金额
     *必填
     *单位为分 只能为整数
     * */
    private Integer total;

    /*
     * @Description
     * 退款币种
     * 必填
     * */
    private String currency = "CNY";
}
