package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Detail {
    /*1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
     2、当订单原价与支付金额不相等，则不享受优惠。
    3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
    示例值：608800*/
    private Integer cost_price; // 订单原价(非必填)
    private String invoice_id; // 商品小票ID(非必填) : 商家小票ID 示例值：微信123
    private Goods_detail[] goods_detail; // 单品列表(非必填) : 单品列表信息 条目个数限制：【1，6000】
}
