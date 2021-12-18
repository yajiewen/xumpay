package com.xumaggregatepayments.boot.wechat.paramobjects.towechat.refundorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 11-43-47
 * @Description 退款出资账户即金额
 * 退款需要从指定账户出资时，传递此参数指定出资金额（币种的最小单位，只能为整数）。
 * 同时指定多个账户出资退款的使用场景需要满足以下条件：
 *   1、未开通退款支出分离产品功能；
 *   2、订单属于分账订单，且分账处于待分账或分账中状态。
 * 参数传递需要满足条件：
 *   1、基本账户可用余额出资金额与基本账户不可用余额出资金额之和等于退款金额；
 *   2、账户类型不能重复。
 * 上述任一条件不满足将返回错误
*/
@Data
public class From {
    /*
    * @Description
    * 必填
    * 下面枚举值多选一。
        枚举值：
        AVAILABLE : 可用余额
        UNAVAILABLE : 不可用余额
    * */
    private String account;

    /*
    * @Description
    * 出资金额
    * 必填
    * 对应账户出资金额
    * */
    private Integer amount;
}
