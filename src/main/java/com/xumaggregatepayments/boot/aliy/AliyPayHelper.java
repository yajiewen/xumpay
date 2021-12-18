package com.xumaggregatepayments.boot.aliy;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;

import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.common.models.*;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.xumaggregatepayments.boot.aliy.beans.CommonParams;
import com.xumaggregatepayments.boot.aliy.params.apppay.received.ReceivedAppPayParams;
import com.xumaggregatepayments.boot.aliy.params.apppay.returned.ReturnedAppPayMessage;
import com.xumaggregatepayments.boot.aliy.params.mobilepay.received.ReceivedMobilePayParams;
import com.xumaggregatepayments.boot.aliy.params.mobilepay.returned.ReturnedMobilePayMessage;
import com.xumaggregatepayments.boot.aliy.params.pcpay.received.ReceivedPcPayParams;
import com.xumaggregatepayments.boot.aliy.params.pcpay.returned.ReturnedPcPayMessage;
import com.xumaggregatepayments.boot.aliy.params.scanpay.received.ReceivedScanPayParams;
import com.xumaggregatepayments.boot.aliy.params.scanpay.returned.ReturnedScanPayMessage;
import com.xumaggregatepayments.boot.wechat.service.paramsoperation.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yajiewen
 * @Date 2021-11-06 15-17-24
 * @Description Pc端支付
*/
@Service
@Slf4j
public class AliyPayHelper {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    CommonParams commonParams;

    /*
    * @Description
    * 支付宝扫码支付
    * */
    public ReturnedScanPayMessage createScanPayOrder(ReceivedScanPayParams receivedScanPayParams){
        ReturnedScanPayMessage returnedScanPayMessage = new ReturnedScanPayMessage();
        try {
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate(receivedScanPayParams.getSubject(), receivedScanPayParams.getOutTradeNo(), receivedScanPayParams.getTotalAmount());
            if(ResponseChecker.success(response)){
                returnedScanPayMessage.setSuccess(true);
                returnedScanPayMessage.setOrderInfo(response.getQrCode());
                return returnedScanPayMessage;
            }else{
                returnedScanPayMessage.setSuccess(false);
                returnedScanPayMessage.setOrderInfo(response.getMsg());
                return returnedScanPayMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            returnedScanPayMessage.setSuccess(false);
            returnedScanPayMessage.setOrderInfo(e.getMessage());
            return returnedScanPayMessage;
        }
    }

    /*
    * @Description
    * 支付宝pc支付
    * */
    public ReturnedPcPayMessage createPcPayOrder(ReceivedPcPayParams receivedPcPayParams){

        ReturnedPcPayMessage returnedPcPayMessage = new ReturnedPcPayMessage();
        try {
            AlipayTradePagePayResponse response = Factory.Payment.Page().pay(receivedPcPayParams.getSubject(), receivedPcPayParams.getOutTradeNo(), receivedPcPayParams.getTotalAmount(), receivedPcPayParams.getReturnUrl());
            if(ResponseChecker.success(response)){
                returnedPcPayMessage.setSuccess(true);
                returnedPcPayMessage.setOrderInfo(response.getBody());
                return returnedPcPayMessage;
            }else{
                returnedPcPayMessage.setSuccess(false);
                returnedPcPayMessage.setOrderInfo(response.getBody());
                return returnedPcPayMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            returnedPcPayMessage.setSuccess(false);
            returnedPcPayMessage.setOrderInfo(e.getMessage());
            return returnedPcPayMessage;
        }
    }

    /*
    * @Description
    * 手机网站支付
    * */
    public ReturnedMobilePayMessage createMobilePayOrder(ReceivedMobilePayParams receivedMobilePayParams){

        ReturnedMobilePayMessage returnedMobilePayMessage = new ReturnedMobilePayMessage();
        try {
            AlipayTradeWapPayResponse response = Factory.Payment.Wap().pay(receivedMobilePayParams.getSubject(), receivedMobilePayParams.getOutTradeNo(), receivedMobilePayParams.getTotalAmount(), receivedMobilePayParams.getQuitUrl(), receivedMobilePayParams.getReturnUrl());
            if(ResponseChecker.success(response)){
                returnedMobilePayMessage.setSuccess(true);
                returnedMobilePayMessage.setOrderInfo(response.getBody());
                return returnedMobilePayMessage;
            }else{
                returnedMobilePayMessage.setSuccess(false);
                returnedMobilePayMessage.setOrderInfo(response.getBody());
                return returnedMobilePayMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            returnedMobilePayMessage.setSuccess(false);
            returnedMobilePayMessage.setOrderInfo(e.getMessage());
            return returnedMobilePayMessage;
        }
    }

    /*
     * @Description
     * 支付宝app支付
     * */
    public ReturnedAppPayMessage createAppPayOrder(ReceivedAppPayParams receivedAppPayParams){

        ReturnedAppPayMessage returnedPcPayMessage = new ReturnedAppPayMessage();
        try {
            AlipayTradeAppPayResponse response = Factory.Payment.App().pay(receivedAppPayParams.getSubject(), receivedAppPayParams.getOutTradeNo(), receivedAppPayParams.getTotalAmount());
            if(ResponseChecker.success(response)){
                returnedPcPayMessage.setSuccess(true);
                returnedPcPayMessage.setOrderInfo(response.getBody());
                return returnedPcPayMessage;
            }else{
                returnedPcPayMessage.setSuccess(false);
                returnedPcPayMessage.setOrderInfo(response.getBody());
                return returnedPcPayMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            returnedPcPayMessage.setSuccess(false);
            returnedPcPayMessage.setOrderInfo(e.getMessage());
            return returnedPcPayMessage;
        }
    }

    /*
    * @Description
    * 交易查询
    * */

    public String queryOrder(String outTradeNo){
        try {
            AlipayTradeQueryResponse response = Factory.Payment.Common().query(outTradeNo);
                return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    /*
    * @Description
    * 关闭交易
    * */

    public String closeOrder(String outTradeNo){
        try{
            AlipayTradeCloseResponse response = Factory.Payment.Common().close(outTradeNo);
            return response.getHttpBody();
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    /*
     * @Description
     * 撤销订单(支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，
     * 支付宝系统会将此订单关闭；如果用户支付成功，支付宝系统会将此订单资金退还给用户。
     * 注意：只有发生支付系统超时或者支付结果未知时可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API)
     * */

    public String cancelOrder(String outTradeNo){
        try{
            AlipayTradeCancelResponse response = Factory.Payment.Common().cancel(outTradeNo);
            return response.getHttpBody();
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    /*
    * @Description
    * 交易退款
    * */
    public String refundOrder(String outTradeNo,String refundAmount){
        try{
            AlipayTradeRefundResponse response = Factory.Payment.Common().refund(outTradeNo, refundAmount);
            return response.getHttpBody();
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }
    }
    
    /*
    * @Description
    * 退款查询
    * @Param outRequestNo : 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
    * */    
    public String queryRefund(String outTradeNo ,String outRequestNo){
        try{
            AlipayTradeFastpayRefundQueryResponse response = Factory.Payment.Common().queryRefund(outTradeNo, outRequestNo);
            return response.getHttpBody();
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

}
