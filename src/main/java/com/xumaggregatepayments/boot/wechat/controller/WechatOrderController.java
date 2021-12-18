package com.xumaggregatepayments.boot.wechat.controller;

import com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder.OrderReceivedRequestParameters;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.closeorder.CloseOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.apporder.AppOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.jsapiorder.JsapiOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.createorder.nativeorder.NativeOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.qureyorder.QueryOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.returned.refundorder.RefunderOrderDealInfo;
import com.xumaggregatepayments.boot.wechat.paramobjects.towechat.refundorder.RefundOrderEntity;
import com.xumaggregatepayments.boot.wechat.service.orderoperation.WechatOrderHelper;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.QueryMethods;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.WechatPayRequestParametersAutoSetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-20
 * @Description
*/ 
@RestController
@Slf4j
public class WechatOrderController {
    @Autowired
    WechatOrderHelper wechatOrderHelper;
    @Autowired
    WechatPayRequestParametersAutoSetter wechatPayRequestParametersAutoSetter;

    @PostMapping("/wechat/native")
    public NativeOrderDealInfo createNativeOrder(@RequestBody OrderReceivedRequestParameters orderReceivedRequestParameters){
        // 创建返回信息对象
        NativeOrderDealInfo nativeOrderDealInfo = wechatOrderHelper.creadeNativeOrder(orderReceivedRequestParameters);
        return nativeOrderDealInfo;
    }

    @PostMapping("/wechat/jsapi")
    public JsapiOrderDealInfo createJsapiOrder(@RequestBody OrderReceivedRequestParameters orderReceivedRequestParameters){
        // 创建返回信息对象
        JsapiOrderDealInfo jsapiOrderDealInfo = wechatOrderHelper.creadeJsapiOrder(orderReceivedRequestParameters);
        return jsapiOrderDealInfo;
    }

    @PostMapping("/wechat/app")
    public AppOrderDealInfo createAppOrder(@RequestBody OrderReceivedRequestParameters orderReceivedRequestParameters){
        // 创建返回信息对象
        AppOrderDealInfo appOrderDealInfo = wechatOrderHelper.creadeAppOrder(orderReceivedRequestParameters);
        return appOrderDealInfo;
    }

/*
* @Description
* 有后端发送mchid
* */
/*  * @Description
    * 微信支付系统生成的订单号查询
    * */
    @GetMapping("/wechat/orderinfom/transaction/{orderNumber}/{mchid}")
    public QueryOrderDealInfo orderNumberInquery(@PathVariable("orderNumber") String orderNumber,
                                               @PathVariable("mchid") String mchid){
        return wechatOrderHelper.orderNumberInquiry(QueryMethods.transaction,orderNumber,mchid);
    }


/*     * @Description
     * 商户订单号查询
     * */
    @GetMapping("/wechat/orderinfom/outtradeno/{orderNumber}/{mchid}")
    public QueryOrderDealInfo outtradeInquery(@PathVariable("orderNumber") String orderNumber,
                                     @PathVariable("mchid") String mchid){
        return wechatOrderHelper.orderNumberInquiry(QueryMethods.out_trade_no,orderNumber,mchid);
    }


    /*
     * @Description
     * 微信支付系统生成的订单号查询(使用默认mchid)
     * */
    @GetMapping("/wechat/orderinfo/transaction/{orderNumber}")
    public QueryOrderDealInfo orderNumberInquery(@PathVariable("orderNumber") String orderNumber){
        return wechatOrderHelper.orderNumberInquiry(QueryMethods.transaction,orderNumber,wechatPayRequestParametersAutoSetter.getMchid());
    }

    /*
     * @Description
     * 商户订单号查询(使用默认mchid)
     * */
    @GetMapping("/wechat/orderinfo/outtradeno/{orderNumber}")
    public QueryOrderDealInfo outtradeInquery(@PathVariable("orderNumber") String orderNumber){
        return wechatOrderHelper.orderNumberInquiry(QueryMethods.out_trade_no,orderNumber,wechatPayRequestParametersAutoSetter.getMchid());
    }

    /**
     * @Author yajiewen
     * @Date 2021-10-29 16-25-17
     * @Description 通过商户订单号关闭订单(使用后端发送的mchid)
     */

    @GetMapping("/wechat/closeorder/{outTradeNo}/{mchid}")
    public CloseOrderDealInfo closeOrder(@PathVariable("outTradeNo") String outTradeNo,
                                         @PathVariable("mchid") String mchid){
        return wechatOrderHelper.closeOrder(outTradeNo,mchid);
    }

    /**
     * @Author yajiewen
     * @Date 2021-10-29 16-25-17
     * @Description 通过商户订单号关闭订单(使用默认mchid)
    */

    @GetMapping("/wechat/closeorder/{outTradeNo}")
    public CloseOrderDealInfo closeOrder(@PathVariable("outTradeNo") String outTradeNo){
        return wechatOrderHelper.closeOrder(outTradeNo,wechatPayRequestParametersAutoSetter.getMchid());
    }

    /**
     * @Author yajiewen
     * @Date 2021-10-30 12-15-38
     * @Description
     *  申请退款
    */
    @PostMapping("/wechat/refund")
    public RefunderOrderDealInfo refundOrder(@RequestBody RefundOrderEntity refundOrderEntity){
        return wechatOrderHelper.refundOrder(refundOrderEntity);
    }

    /**
     * @Author yajiewen
     * @Date 2021-10-30 12-52-00
     * @Description
     * 查询退款
    */
    @GetMapping("/wechat/refundinfo/{outTradeNo}")
    public String getRefundInfo(@PathVariable("outTradeNo") String outTradeNo){
        return wechatOrderHelper.qureyRefundOrder(outTradeNo);
    }
}
