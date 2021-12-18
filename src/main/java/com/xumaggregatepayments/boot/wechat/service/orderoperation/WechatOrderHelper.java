package com.xumaggregatepayments.boot.wechat.service.orderoperation;

import com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder.OrderReceivedRequestParameters;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.closeorder.CloseOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.apporder.AppEntity;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.apporder.AppOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.jsapiorder.JsapiEntity;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.jsapiorder.JsapiOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.nativeorder.NativeEntity;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.nativeorder.NativeOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.queryrefundorder.QueryRefundOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder.QueryOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder.QueryOrderInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.refundorder.RefunderOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.towechat.closeorder.CloseOrderEntity;
import com.xumaggregatepayments.boot.wechat.paramobjects.towechat.refundorder.RefundOrderEntity;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author yajiewen
 * @Date 2021-10-27 14-24-53
 * @Description 微信订单操作类
*/
@Service
@Slf4j
public class WechatOrderHelper {
    @Autowired
    WechatPayRequestParametersAutoSetter wechatPayRequestParametersAutoSetter;
    @Autowired
    JsonHelper jsonHelper;
    @Autowired
    PaymentMethodUrlMapper paymentMethodUrlMapper;
    @Autowired
    HttpClient httpClient;
    @Autowired
    HttpPostGetter httpPostGetter;
    @Autowired
    HttpGetGetter httpGetGetter;
    /*
    * @Description 创建native订单
    * */
    public NativeOrderDealInfo creadeNativeOrder(OrderReceivedRequestParameters orderReceivedRequestParameters){

        // 创建返回信息对象
        NativeOrderDealInfo nativeOrderDealInfo = new NativeOrderDealInfo();
        // 获取对应支付方式的url
        String paymentUrl = paymentMethodUrlMapper.paymentMethodUrlGetter(PaymentMethods.nativePay);
        // 自动填充系统配置参数 appid mchid notify_url 若传入,则进行参数检测
        if( wechatPayRequestParametersAutoSetter.paramsAutoSet(orderReceivedRequestParameters) ){
            try {
                HttpResponse response = httpClient.execute(httpPostGetter.getHttpPost(paymentUrl, jsonHelper.objectToJson(orderReceivedRequestParameters)));
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) { //处理成功
                    String orderInfo = EntityUtils.toString(response.getEntity());
                    nativeOrderDealInfo.setOrderInfo(jsonHelper.jsonToObject(orderInfo, NativeEntity.class));
                    nativeOrderDealInfo.setOtherInfo("请求成功");
                    return nativeOrderDealInfo;
                } else if (statusCode == 204) { //处理成功，无返回Body
                    nativeOrderDealInfo.setOtherInfo("请求成功,无返回body");
                    return nativeOrderDealInfo;
                } else { // 处理失败
                    String orderInfo = EntityUtils.toString(response.getEntity());
                    nativeOrderDealInfo.setSuccess(false);
                    nativeOrderDealInfo.setOtherInfo("failed,resp code = " + statusCode +"\n" + orderInfo);
                    return nativeOrderDealInfo;
                }
            } catch (ClientProtocolException e) {
                log.error(e.toString());
                nativeOrderDealInfo.setSuccess(false);
                nativeOrderDealInfo.setOtherInfo("failed,ClientProtocolException");
                return nativeOrderDealInfo;
            } catch (IOException e) {
                log.error("native 下单toString 方法出错");
                nativeOrderDealInfo.setSuccess(false);
                nativeOrderDealInfo.setOtherInfo("native 下单toString 方法出错");
                return nativeOrderDealInfo;
            }
        }else{
            log.error("自动填充的三个参数检测错误appid,mchid,或notify_url");
            nativeOrderDealInfo.setSuccess(false);
            nativeOrderDealInfo.getParamsDealInfo().setParamsError(true);
            nativeOrderDealInfo.setOtherInfo("自动填充的三个参数检测错误appid,mchid,或notify_url");
            return nativeOrderDealInfo;
        }
    }

    /*
     * @Description 创建jsapi订单
     * */
    public JsapiOrderDealInfo creadeJsapiOrder(OrderReceivedRequestParameters orderReceivedRequestParameters){

        // 创建返回信息对象
        JsapiOrderDealInfo jsapiOrderDealInfo = new JsapiOrderDealInfo();
        // 获取对应支付方式的url
        String paymentUrl = paymentMethodUrlMapper.paymentMethodUrlGetter(PaymentMethods.jsapiPay);
        // 自动填充系统配置参数 appid mchid notify_url 若传入,则进行参数检测
        if( wechatPayRequestParametersAutoSetter.paramsAutoSet(orderReceivedRequestParameters) ){
            try {
                HttpResponse response = httpClient.execute(httpPostGetter.getHttpPost(paymentUrl, jsonHelper.objectToJson(orderReceivedRequestParameters)));
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) { //处理成功
                    String orderInfo = EntityUtils.toString(response.getEntity());
                    jsapiOrderDealInfo.setOrderInfo(jsonHelper.jsonToObject(orderInfo, JsapiEntity.class));
                    jsapiOrderDealInfo.setOtherInfo("请求成功");
                    return jsapiOrderDealInfo;
                } else if (statusCode == 204) { //处理成功，无返回Body
                    jsapiOrderDealInfo.setOtherInfo("请求成功,无返回body");
                    return jsapiOrderDealInfo;
                } else { // 处理失败
                    String orderInfo = EntityUtils.toString(response.getEntity());
                    jsapiOrderDealInfo.setSuccess(false);
                    jsapiOrderDealInfo.setOtherInfo("failed,resp code = " + statusCode +"\n" + orderInfo);
                    return jsapiOrderDealInfo;
                }
            } catch (ClientProtocolException e) {
                log.error(e.toString());
                jsapiOrderDealInfo.setSuccess(false);
                jsapiOrderDealInfo.setOtherInfo("failed,ClientProtocolException");
                return jsapiOrderDealInfo;
            } catch (IOException e) {
                log.error("jsapi 下单toString 方法出错");
                jsapiOrderDealInfo.setSuccess(false);
                jsapiOrderDealInfo.setOtherInfo("jsapi 下单toString 方法出错");
                return jsapiOrderDealInfo;
            }
        }else{
            log.error("自动填充的三个参数检测错误appid,mchid,或notify_url");
            jsapiOrderDealInfo.setSuccess(false);
            jsapiOrderDealInfo.getParamsDealInfo().setParamsError(true);
            jsapiOrderDealInfo.setOtherInfo("自动填充的三个参数检测错误appid,mchid,或notify_url");
            return jsapiOrderDealInfo;
        }
    }

    /*
     * @Description 创建app订单
     * */
    public AppOrderDealInfo creadeAppOrder(OrderReceivedRequestParameters orderReceivedRequestParameters){

        // 创建返回信息对象
        AppOrderDealInfo appOrderDealInfo = new AppOrderDealInfo();
        // 获取对应支付方式的url
        String paymentUrl = paymentMethodUrlMapper.paymentMethodUrlGetter(PaymentMethods.appPay);
        // 自动填充系统配置参数 appid mchid notify_url 若传入,则进行参数检测
        if( wechatPayRequestParametersAutoSetter.paramsAutoSet(orderReceivedRequestParameters) ){
            try {
                HttpResponse response = httpClient.execute(httpPostGetter.getHttpPost(paymentUrl, jsonHelper.objectToJson(orderReceivedRequestParameters)));
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) { //处理成功
                    String orderInfo = EntityUtils.toString(response.getEntity());
                    appOrderDealInfo.setOrderInfo(jsonHelper.jsonToObject(orderInfo, AppEntity.class));
                    appOrderDealInfo.setOtherInfo("请求成功");
                    return appOrderDealInfo;
                } else if (statusCode == 204) { //处理成功，无返回Body
                    appOrderDealInfo.setOtherInfo("请求成功,无返回body");
                    return appOrderDealInfo;
                } else { // 处理失败
                    String orderInfo = EntityUtils.toString(response.getEntity());
                    appOrderDealInfo.setSuccess(false);
                    appOrderDealInfo.setOtherInfo("failed,resp code = " + statusCode +"\n" + orderInfo);
                    return appOrderDealInfo;
                }
            } catch (ClientProtocolException e) {
                log.error(e.toString());
                appOrderDealInfo.setSuccess(false);
                appOrderDealInfo.setOtherInfo("failed,ClientProtocolException");
                return appOrderDealInfo;
            } catch (IOException e) {
                log.error("app 下单toString 方法出错");
                appOrderDealInfo.setSuccess(false);
                appOrderDealInfo.setOtherInfo("app 下单toString 方法出错");
                return appOrderDealInfo;
            }
        }else{
            log.error("自动填充的三个参数检测错误appid,mchid,或notify_url");
            appOrderDealInfo.getParamsDealInfo().setParamsError(true);
            appOrderDealInfo.setSuccess(false);
            appOrderDealInfo.setOtherInfo("自动填充的三个参数检测错误appid,mchid,或notify_url");
            return appOrderDealInfo;
        }
    }

    /*
    * @Description
    * 微信支付订单号查询(订单号为微信支付系统生成的订单号)
    * */
    public QueryOrderDealInfo orderNumberInquiry(String queryMethod,String transaction_id, String mchid){
        String queryMethodUrl =  wechatPayRequestParametersAutoSetter.getQueryMthods().get(queryMethod);

            String url = String.format(queryMethodUrl,transaction_id,mchid);
            QueryOrderDealInfo queryOrderDealInfo = new QueryOrderDealInfo();
            try {
                HttpResponse response = httpClient.execute(httpGetGetter.getHttpGet(url));
                String responseEntity = EntityUtils.toString(response.getEntity());

                queryOrderDealInfo.setOrderInfo(jsonHelper.jsonToObject(responseEntity, QueryOrderInfo.class));

                if (queryOrderDealInfo.getOrderInfo().getCode() != null ){
                    queryOrderDealInfo.setOtherInfo("查询失败");
                    queryOrderDealInfo.setSuccess(false);
                }else{
                    queryOrderDealInfo.setOtherInfo("查询成功");
                }
                return queryOrderDealInfo;
            } catch (IOException e) {
                log.error(e.toString());
                queryOrderDealInfo.setSuccess(false);
                queryOrderDealInfo.setOtherInfo(e.toString());
                return queryOrderDealInfo;
            }
        }


    /*
    * @Description
    * 商户订单号关闭订单 : 五分钟之后微信那边才允许关闭(五分钟内使用此接口微信返回关闭成功,但是实则不成功)
    * */
    public CloseOrderDealInfo closeOrder(String outTradeNo,String mchid){
        String closeUrl = wechatPayRequestParametersAutoSetter.getClose_order_url();
        String url = String.format(closeUrl,outTradeNo);
        CloseOrderEntity closeOrderEntity = new CloseOrderEntity(mchid);

        try {
            HttpResponse response = httpClient.execute(httpPostGetter.getHttpPost(url,jsonHelper.objectToJson(closeOrderEntity)));
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 204){
                CloseOrderDealInfo closeOrderDealInfo = new CloseOrderDealInfo();
                closeOrderDealInfo.setClose(true);
                closeOrderDealInfo.setOtherInfo("关闭成功");
                return closeOrderDealInfo;
            }else{
                String entity = EntityUtils.toString(response.getEntity());
                log.error(entity);
                CloseOrderDealInfo closeOrderDealInfo = new CloseOrderDealInfo();
                closeOrderDealInfo.setClose(false);
                closeOrderDealInfo.setOtherInfo(entity);
                return closeOrderDealInfo;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            CloseOrderDealInfo closeOrderDealInfo = new CloseOrderDealInfo();
            closeOrderDealInfo.setClose(false);
            closeOrderDealInfo.setOtherInfo(e.getMessage());
            return closeOrderDealInfo;
        }
    }

    /*
    * @Description
    * 申请退款
    * */
    public RefunderOrderDealInfo refundOrder(RefundOrderEntity refundOrderEntity){
        /*
        * @Description
        * 未传递退款结果通知url 则使用系统默认配置
        * */
        if(refundOrderEntity.getNotify_url() == null){
            refundOrderEntity.setNotify_url(wechatPayRequestParametersAutoSetter.getRefund_notify_url());
        }
        String refundUrl = wechatPayRequestParametersAutoSetter.getRefund_order_url();
        RefunderOrderDealInfo refunderOrderDealInfo = new RefunderOrderDealInfo();
        try{
            HttpResponse response = httpClient.execute(httpPostGetter.getHttpPost(refundUrl,jsonHelper.objectToJson(refundOrderEntity)));
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                String orderInfo = EntityUtils.toString(response.getEntity());
                refunderOrderDealInfo.setOrderInfo(orderInfo);
                return refunderOrderDealInfo;
            }else{
                String orderInfo = EntityUtils.toString(response.getEntity());
                refunderOrderDealInfo.setOrderInfo(orderInfo);
                refunderOrderDealInfo.setSuccess(false);
                return refunderOrderDealInfo;
            }
        }catch(IOException e){
            log.error(e.toString());
            refunderOrderDealInfo.setSuccess(false);
            return refunderOrderDealInfo;
        }
    }
    /*
    * @Description
    * 查询退款
    * */
    public String qureyRefundOrder(String outTradeNo){
        String refundUrl = wechatPayRequestParametersAutoSetter.getQuery_refund_order_url();
        String url = String.format(refundUrl,outTradeNo);
        QueryRefundOrderDealInfo queryRefundOrderDealInfo = new QueryRefundOrderDealInfo();
        try{
            HttpResponse response = httpClient.execute(httpGetGetter.getHttpGet(url));
            return EntityUtils.toString(response.getEntity());

        }catch(IOException e){
            log.error(e.toString());
            return e.getMessage();
        }
    }
}
