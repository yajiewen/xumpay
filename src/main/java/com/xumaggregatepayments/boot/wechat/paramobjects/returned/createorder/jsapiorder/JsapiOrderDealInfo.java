package com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.jsapiorder;

import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.ParamsDealInfo;
import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-27 16-18-46
 * @Description 返回给后端的jsapi下单请求结果信息
*/
@Data
public class JsapiOrderDealInfo {
    private ParamsDealInfo paramsDealInfo = new ParamsDealInfo(); // 请求参数检测信息
    private boolean success = true; // 是否请求成功
    private String otherInfo; // 保存时报
    private JsapiEntity orderInfo;
}
