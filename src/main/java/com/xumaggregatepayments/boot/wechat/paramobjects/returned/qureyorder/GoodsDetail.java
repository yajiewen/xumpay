package com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 13-50-09
 * @Description 优惠信息中的单品列表
*/
@Data
public class GoodsDetail {
    private String goods_id;
    private Integer quantity;
    private Integer unit_price;
    private Integer discount_amount;
    private String goods_remark;
}
