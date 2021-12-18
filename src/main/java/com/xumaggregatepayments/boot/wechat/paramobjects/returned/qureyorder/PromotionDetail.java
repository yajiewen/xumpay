package com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 13-46-19
 * @Description 查询订单中的优惠功能
*/
@Data
public class PromotionDetail {
    private String coupon_id;
    private String name;
    private String scope;
    private String type;
    private Integer amount;
    private String stock_id;
    private Integer wechatpay_contribute;
    private Integer merchant_contribute;
    private Integer other_contribute;
    private String currency;
    private GoodsDetail[] goods_detail;

}
