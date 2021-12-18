package com.xumaggregatepayments.boot.wechat.paramobjects.towechat.refundorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 11-30-22
 * @Description 保存退款请求参数
*/
@Data
public class RefundOrderEntity {
    /*
    * @Description
    * 微信支付订单号
    * 注意: 微信支付订单号 和下面商户单号 二选一 填一个
     * */
    private String transaction_id;

    /*
    * @Description
    * 商户订单号
    * */
    private String out_trade_no;

    /*
    * @Description
    * 商户退款单号
    * 必填
    * */
    private String out_refund_no;

    /*
    * @Description
    * 退款原因
    * 非必填
    * */
    private String reason;

    /*
    * @Description
    * 退款结果回调url
    * 非必填
    * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的这个地址。
    * 若未传递该参数将会使用系统默认配置
    * */
    private String notify_url;

    /*
    * @Description
    * 退款资金来源
    * 非必填
    * 传递此参数则使用对应的资金账户退款，否则默认使用未结算资金退款（仅对老资金流商户适用
    * */
    private String funds_account;

    /*
    * @Description
    * 金额信息
    * 必填
    * */
    private Amount amount;

    /*
    * @Description
    * 退款商品
    * 非必填
    * */
    private GoodsDetail[] goods_detail;
}
