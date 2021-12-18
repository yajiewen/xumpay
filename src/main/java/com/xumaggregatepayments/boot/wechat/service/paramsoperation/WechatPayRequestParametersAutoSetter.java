package com.xumaggregatepayments.boot.wechat.service.paramsoperation;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder.OrderReceivedRequestParameters;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PrivateKey;
import java.util.HashMap;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-56-55
 * @Description
*/ 
@Slf4j
@Data
@Component
@Scope("singleton")
@ConfigurationProperties(prefix = "wechatpay")
public class WechatPayRequestParametersAutoSetter {
    /*由微信生成的应用ID，全局唯一。请求基础下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID*/
    private String appid;   // 应用ID(必填(若请求未传入,系统自动填充配置中的appid))
    private String mchid;   // 直连商户号(必填(若请求未传入,系统自动填充配置中的mchid)) : 直连商户的商户号，由微信支付生成并下发。
    private String notify_url; // 通知地址(支付结果通知地址)(必填(若请求未传入,系统自动填充 配置中的notify_url)) : 通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。
    /*
    * @Description
    * 退款结果回调url
    * */
    private String refund_notify_url;

    /*
    * @Description
    * 微信退款url
    * */
    private String refund_order_url;

    /*
    * @Description
    * 查询退款 url
    * */
    private String query_refund_order_url;

    private String api_v3_key; // apiV3 密钥
    /**
     * MD5密钥，安全检验码，由数字和字母组成的32位字符串组成
     */
    private String mchapikey;
    private String merchant_serial_number; //商户API证书的证书序列号
    private String merchant_private_key_path; // 微信支付平台 商户私钥路径
    private PrivateKey merchant_private_key; // 商户私匙
    private String union_order_url; // 微信统一下单地址
    private String native_order_url; // Native 方式下单地址
    private String app_order_url; // App 方式下单地址

    /*
    * @Description
    * */
    private String close_order_url;

    /*
    * @Description
    * 微信查询订单地址
    * */
    private HashMap<String,String> queryMthods;
    /*
    * @Description
    * 保存支付方式地址
    * */
    private HashMap<String,String> paymentUrls;



    // 加载商户私匙
    @PostConstruct
    public void loadMerchantPrivateKey(){
        try {
            merchant_private_key  = PemUtil.loadPrivateKey(new FileInputStream(merchant_private_key_path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*前面三个参数appid , mchid , notify_url 的自动填入*/
    public boolean paramsAutoSet(OrderReceivedRequestParameters orderReceivedRequestParameters){
        // 先检测 请求是否传入了这三个参数,传入的参数判断是否符合要求,没传入的自动填充yaml配置
        if( appidAutoSet(orderReceivedRequestParameters) & mchidAutoSet(orderReceivedRequestParameters) & notify_urlAutoSet(orderReceivedRequestParameters)){
            return true;
        }else{
            return false;
        }

    }

    // appid 自动配置与检测
    public boolean appidAutoSet(OrderReceivedRequestParameters orderReceivedRequestParameters){
        if (orderReceivedRequestParameters.getAppid() == null){ // 未传递appid使用系统默认配置中的 appid
            orderReceivedRequestParameters.setAppid(appid);
            return true;
        }else{
            if(orderReceivedRequestParameters.getAppid().length() >=1 && orderReceivedRequestParameters.getAppid().length() <= 32){
                return true;
            }else{
                log.error("appid 不符合要求");
                return false;
            }
        }
    }

    // mchid 自动配置与检测
    public boolean mchidAutoSet(OrderReceivedRequestParameters orderReceivedRequestParameters){
        if (orderReceivedRequestParameters.getMchid() == null){ // 未传递mchid 使用系统默认配置中的mchid
            orderReceivedRequestParameters.setMchid(mchid);
            return true;
        }else{
            if(orderReceivedRequestParameters.getMchid().length() >=1 && orderReceivedRequestParameters.getMchid().length() <= 32){
                return true;
            }else{
                log.error("mchid 不符合要求");
                return false;
            }
        }
    }

    // notify_url 自动检测与配置
    public boolean notify_urlAutoSet(OrderReceivedRequestParameters orderReceivedRequestParameters){
        if (orderReceivedRequestParameters.getNotify_url() == null){ // 未传递回调通知地址,使用系统默认回调通知地址
            orderReceivedRequestParameters.setNotify_url(notify_url);
            return true;
        }else{
            if(orderReceivedRequestParameters.getNotify_url().length() >=1 && orderReceivedRequestParameters.getNotify_url().length() <= 32){
                return true;
            }else{
                log.error("notify_url 不符合要求");
                return false;
            }
        }
    }
}
