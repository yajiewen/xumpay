package com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.apporder;

import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.ParamsDealInfo;
import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-28 13-51-07
 * @Description
*/
@Data
public class AppOrderDealInfo {
    private ParamsDealInfo paramsDealInfo = new ParamsDealInfo(); // 请求参数检测信息
    private boolean success = true; // 是否请求成功
    private String otherInfo; // 保存时报
    private AppEntity orderInfo;
}
