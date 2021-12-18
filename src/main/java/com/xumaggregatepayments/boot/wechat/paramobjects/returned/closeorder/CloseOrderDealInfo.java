package com.xumaggregatepayments.boot.wechat.paramobjects.returned.closeorder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-29 16-20-21
 * @Description 用于保存返回给后端的关闭订单处理信息
*/
@Data
public class CloseOrderDealInfo {
    private boolean close;
    private String otherInfo;
}
