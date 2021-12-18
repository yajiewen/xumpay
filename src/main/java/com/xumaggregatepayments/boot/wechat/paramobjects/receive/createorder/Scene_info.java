package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Scene_info {
    private String payer_client_ip; // 用户终端IP(必填) : 用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。示例值：14.23.150.211
    private String device_id; // 商户端设备号(非必填) : 商户端设备号（门店号或收银设备ID）。示例值：013467007045764
    private Store_info store_info; // 商户门店信息(非必填) :
}
