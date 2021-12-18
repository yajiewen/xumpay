package com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder;

import lombok.Data;
import lombok.ToString;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-19
 * @Description
*/ 
@Data
@ToString
public class OrderReceivedRequestParameters {
    /*由微信生成的应用ID，全局唯一。请求基础下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID*/
    private String appid;   // 应用ID(必填(若请求未传入,系统自动填充))
    private String mchid;   // 直连商户号(必填(若请求未传入,系统自动填充)) : 直连商户的商户号，由微信支付生成并下发。
    private String notify_url; // 通知地址(支付结果通知地址)(必填(若请求未传入,系统自动填充)) : 通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。

    private String description; // 商品描述(必填) : 商品描述示例值：Image形象店-深圳腾大-QQ公仔
    private String out_trade_no; // 商户订单号(必填) : 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一示例值：1217752501201407033233368018
    /*交易结束时间
    * 订单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，
    * T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
    * 例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
    订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
    * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则。
    建议：最短失效时间间隔大于1分钟
    示例值：2018-06-08T10:34:56+08:00*/
    private String time_expire;  // 交易结束时间(非必填)
    private String goods_tag;   // 订单优惠标记(非必填) : 订单优惠标记 示例值：WXG
    private Amount amount; // 订单金额(必填) : 订单金额信息
    private Detail detail; // 优惠功能(非必填) : 优惠功能
    private Scene_info scene_info; // 场景信息(非必填) : 支付场景描述
    private Settle_info settle_info; // 结算信息(非必填) : 结算信息

    private Payer payer; // 支付者信息(jsapi 支付必填)

}
