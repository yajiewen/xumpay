package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Goods_detail {
    private String merchant_goods_id; // 商户侧商品编码(必填) : 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。示例值：1246464644
    private String wechatpay_goods_id; // 微信支付商品编码(非必填) : 微信支付定义的统一商品编号（没有可不传）示例值：1001
    private String goods_name; // 商品名称(非必填) : 商品的实际名称 示例值：iPhoneX 256G
    private Integer quantity; // 商品数量(必填) : 用户购买的数量 示例值：1
    private Integer unit_price; // 商品单价(必填) : 商品单价，单位为分 示例值：828800
}
