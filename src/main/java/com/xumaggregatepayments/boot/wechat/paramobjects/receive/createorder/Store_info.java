package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Store_info {
    private String id; // 门店编号(必填) :商户侧门店编号 示例值：0001
    private String name; // 门店名称(非必填) : 商户侧门店名称 示例值：腾讯大厦分店
    private String area_code; // 地区邮编(非必填) : 地区编码，详细请见省市区编号对照表。示例值：440305
    private String address; // 详细地址(非必填) : 详细的商户门店地址 示例值：广东省深圳市南山区科技中一道10000号
}
