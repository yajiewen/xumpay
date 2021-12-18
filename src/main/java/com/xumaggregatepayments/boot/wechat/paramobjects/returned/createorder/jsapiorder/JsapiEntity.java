package com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.jsapiorder;

import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-27 16-16-59
 * @Description 用于保存微信返回的预支付id
*/
@Data
public class JsapiEntity {
    private String prepay_id;
}
