package com.xumaggregatepayments.boot.aliy.controller;

import com.xumaggregatepayments.boot.aliy.AliyPayHelper;
import com.xumaggregatepayments.boot.aliy.params.apppay.received.ReceivedAppPayParams;
import com.xumaggregatepayments.boot.aliy.params.apppay.returned.ReturnedAppPayMessage;
import com.xumaggregatepayments.boot.aliy.params.pcpay.received.ReceivedPcPayParams;
import com.xumaggregatepayments.boot.aliy.params.pcpay.returned.ReturnedPcPayMessage;
import com.xumaggregatepayments.boot.aliy.params.mobilepay.received.ReceivedMobilePayParams;
import com.xumaggregatepayments.boot.aliy.params.mobilepay.returned.ReturnedMobilePayMessage;
import com.xumaggregatepayments.boot.aliy.params.scanpay.received.ReceivedScanPayParams;
import com.xumaggregatepayments.boot.aliy.params.scanpay.returned.ReturnedScanPayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yajiewen
 * @Date 2021-11-06 16-04-12
 * @Description 支付宝支付controller
*/
@RestController
public class AliyPayController {
    @Autowired
    AliyPayHelper aliyPayHelper;
    /*
    * @Description
    * 扫码支付
    * */
    @PostMapping("/aliy/scanpay")
    public ReturnedScanPayMessage createScanPayOrder(@RequestBody ReceivedScanPayParams receivedScanPayParams){
        return aliyPayHelper.createScanPayOrder(receivedScanPayParams);
    }

    /*
    * @Description
    * pc支付
    * */
    @PostMapping("/aliy/pcpay")
    public ReturnedPcPayMessage createPcPayOrder(@RequestBody ReceivedPcPayParams receivedPcPayParams){
        return aliyPayHelper.createPcPayOrder(receivedPcPayParams);
    }

    /*
    * @Description
    * 手机网站支付
    * */
    @PostMapping("/aliy/mobilepay")
    public ReturnedMobilePayMessage createScanPayOrder(@RequestBody ReceivedMobilePayParams receivedMobilePayParams){
        System.out.println(receivedMobilePayParams);
        return aliyPayHelper.createMobilePayOrder(receivedMobilePayParams);
    }

    /*
    * @Description
    * app支付
    * */

    @PostMapping("/aliy/apppay")
    public ReturnedAppPayMessage createScanPayOrder(@RequestBody ReceivedAppPayParams receivedAppPayParams){
        return aliyPayHelper.createAppPayOrder(receivedAppPayParams);
    }

    /*
    * @Description
    * 订单查询 (测试为:订单号不存在返回null)
    * */
    @GetMapping("/aliy/query/{outTradeNo}")
    public String queryOrder(@PathVariable("outTradeNo") String outTradeNo){
        return aliyPayHelper.queryOrder(outTradeNo);
    }

    /*
    * @Description
    * 关闭交易 (用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。)
    * */
    @GetMapping("/aliy/close/{outTradeNo}")
    public String closeOrder(@PathVariable("outTradeNo") String outTradeNo){
        return aliyPayHelper.closeOrder(outTradeNo);
    }

    /*
    * @Description
    * 撤销订单(支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，
    * 支付宝系统会将此订单关闭；如果用户支付成功，支付宝系统会将此订单资金退还给用户。
    * 注意：只有发生支付系统超时或者支付结果未知时可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API)
    * */

    @GetMapping("/aliy/cancel/{outTradeNo}")
    public String cancelOrder(@PathVariable("outTradeNo") String outTradeNo){
        return aliyPayHelper.cancelOrder(outTradeNo);
    }


    /*
    * @Description
    * 订单退款
    * */
    @GetMapping("/aliy/refund/{outTradeNo}/{refundAmount}")
    public String refund(@PathVariable("outTradeNo") String outTradeNo,
                         @PathVariable("refundAmount") String refundAmount){
        return aliyPayHelper.refundOrder(outTradeNo,refundAmount);
    }

    /*
    * @Description
    * 退款查询
    * outRequestNo : 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
    * */

    @GetMapping("/aliy/queryrefund/{outTradeNo}")
    public String queryRefund(@PathVariable("outTradeNo") String outTradeNo){
        return aliyPayHelper.queryRefund(outTradeNo,outTradeNo);
    }


}
