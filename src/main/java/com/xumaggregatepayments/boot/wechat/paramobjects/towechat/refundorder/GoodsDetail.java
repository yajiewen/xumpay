package com.xumaggregatepayments.boot.wechat.paramobjects.towechat.refundorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 11-53-28
 * @Description 退款商品
 * 指定商品退款需要传此参数，其他场景无需传递
*/
@Data
public class GoodsDetail {
    /*
    * @Description
    * 商户侧商品编码
    * 必填
    * */
    private String merchant_goods_id;

    /*
    * @Description
    * 微信支付商品编码
    * 非必填
    * */
    private String wechatpay_goods_id;

    /*
    * @Description
    * 商品名称
    * 非必填
    * */
    private String goods_name;

    /*
    * @Description
    * 商品单价
    * 必填
    * 单位为分
    * */
    private Integer unit_price;

    /*
    * @Description
    * 商品退款金额
    * 必填
    * 单位为分
    * */
    private Integer refund_amount;

    /*
    * @Description
    * 商品退款数量
    * 必填
    * */
    private Integer refund_quantity;

}
