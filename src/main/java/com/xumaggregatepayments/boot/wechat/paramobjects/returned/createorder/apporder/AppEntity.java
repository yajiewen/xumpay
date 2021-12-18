package com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.apporder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-28 13-51-07
 * @Description 用于保存微信返回的预支付id
*/
@Data
public class AppEntity {
    private String prepay_id;
}
