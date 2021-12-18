package com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder;

import com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder.Payer;
import lombok.Data;

/**
 * @Author yajiewen
 * @Date 2021-10-30 13-38-53
 * @Description 保存 查询订单返回的信息
*/
@Data
public class QueryOrderInfo {

    private String code;
    private String message;

    private String appid;
    private String mchid;
    private String out_trade_no;
    private String transaction_id;
    private String trade_type;
    private String trade_state;
    private String trade_state_desc;
    private String bank_type;
    private String attach;
    private String success_time;

    private Payer payer;
    private Amount amount;
    private SceneInfo scene_info;
    private PromotionDetail[] promotion_detail;
}
