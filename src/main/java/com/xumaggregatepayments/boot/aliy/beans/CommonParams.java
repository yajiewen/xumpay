package com.xumaggregatepayments.boot.aliy.beans;

import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author yajiewen
 * @Date 2021-11-06 13-59-52
 * @Description 公共请求参数
*/
@Component
@ConfigurationProperties(prefix = "aliypay")
@Data
@ToString
public class CommonParams {
    /*
    * @Description
    * 协议
    * */
    private String protocol = "https";

    /*
    * @Description
    * gatewayHost
    * */
    private String gateway_host;

    /*
    * @Description
    * 签名算法
    * */
    private String signType = "RSA2";

    /*
     * @Description
     * 必填
     * 支付宝分配给开发者的应用ID
     * */
    private String app_id;

    /*
    * @Description
    * 应用私钥
    * */
    private String merchant_private_key;

    /*
    * @Description
    * 应用公钥证书文件路径
    * */
    private String merchant_cert_path;

    /*
    * @Description
    * 支付宝公钥证书文件路径
    * */
    private String alipay_cert_path;

    /*
    * @Description
    * 支付宝根证书文件路径
    * */
    private String alipay_rootcert_path;

    /*
    * @Description
    * 支付宝公钥
    * */
    private String alipay_public_key;

    /*
    * @Description
    * 回调通知地址
    * 非必填
    * */
    private String notify_url;

    /*
    * @Description
    * AES密钥
    * 非必填
    * */
    private String encrypt_key;

    public Config getOptions(){
        Config config = new Config();
        config.protocol = protocol;
        config.gatewayHost = gateway_host;
        config.signType = signType;
        config.appId = app_id;

        config.merchantPrivateKey = merchant_private_key;
        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        if(merchant_cert_path != null){
            config.merchantCertPath = merchant_cert_path;
        }
        if(alipay_cert_path != null){
            config.alipayCertPath = alipay_cert_path;
        }
        if(alipay_rootcert_path != null){
            config.alipayRootCertPath = alipay_rootcert_path;
        }
        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = alipay_public_key;
        //可设置异步通知接收服务地址（可选）
        if(notify_url != null) {
            config.notifyUrl = notify_url;
        }
        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        if(encrypt_key != null) {
            config.encryptKey = encrypt_key;
        }
        return config;
    }

}
