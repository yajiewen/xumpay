package com.xumaggregatepayments.boot.aliy.params.pcpay.returned;

import lombok.Data;
/**
 * @Author yajiewen
 * @Date 2021-11-06 15-40-27
 * @Description 返回给后端的数据
*/
@Data
public class ReturnedPcPayMessage {
    private boolean success;
    private String orderInfo;

}
