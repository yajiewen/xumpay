package com.xumaggregatepayments.boot.wechat.service.paramsoperation;

import com.xumaggregatepayments.boot.wechat.paramobjects.receive.createorder.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-19
 * @Description
*/ 
@Service
@Slf4j
public class ParamsValidater {
    /*
    * 对获取到的请求参数进行合法性验证*/
    // 检测请求参数是否符合要求
    public boolean validate(OrderReceivedRequestParameters orderReceivedRequestParameters, String paymentMethod){
        if(Objects.equals(paymentMethod,PaymentMethods.nativePay) ||
                Objects.equals(paymentMethod,PaymentMethods.appPay)){
            if(descriptionValidation(orderReceivedRequestParameters) &
                    out_trade_noValidation(orderReceivedRequestParameters) &
                    time_expireValidation(orderReceivedRequestParameters) &
                    goods_tagValidation(orderReceivedRequestParameters) &
                    amountValidation(orderReceivedRequestParameters) &
                    detailValidation(orderReceivedRequestParameters) &
                    scene_infoValidation(orderReceivedRequestParameters) &
                    settle_infoValidation(orderReceivedRequestParameters)){
                return true;
            }else{
                return false;
            }
        }

        if(Objects.equals(paymentMethod,PaymentMethods.jsapiPay)){
            if(descriptionValidation(orderReceivedRequestParameters) &
                    out_trade_noValidation(orderReceivedRequestParameters) &
                    time_expireValidation(orderReceivedRequestParameters) &
                    goods_tagValidation(orderReceivedRequestParameters) &
                    amountValidation(orderReceivedRequestParameters) &
                    detailValidation(orderReceivedRequestParameters) &
                    scene_infoValidation(orderReceivedRequestParameters) &
                    settle_infoValidation(orderReceivedRequestParameters) &
                    payerValidation(orderReceivedRequestParameters)){
                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    /*
     * 各个参数的检测方法
     * */
    // description
    public boolean descriptionValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        String description = orderReceivedRequestParameters.getDescription();
        if (description != null && description.length() >=1 &&
                description.length() <= 127){
            return true;
        }else{
            log.error("description 不符合要求");
            return false;
        }
    }

    // Payer
    public boolean payerValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        Payer payer = orderReceivedRequestParameters.getPayer();
        if(payer == null){
            log.error("payer 不符合要求");
            return false;
        }else {
            if(payer.getOpenid() !=null && payer.getOpenid().length() >= 1
                    && payer.getOpenid().length() <= 128 ){
                return true;
            }else{
                log.error("payer 不符合要求");
                return false;
            }
        }
    }

    // out_trade_no
    public boolean out_trade_noValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        String out_trade_no = orderReceivedRequestParameters.getOut_trade_no();
        if(out_trade_no != null && out_trade_no.length() >=6 && out_trade_no.length() <= 32){
            return true;
        }else{
            log.error("out_trade_no 不符合要求");
            return false;
        }
    }

    // time_expire
    public boolean time_expireValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        String time_expire = orderReceivedRequestParameters.getTime_expire();
        if(time_expire != null){
            if(time_expire.length() >=1 && time_expire.length() <= 64){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                try {
                    simpleDateFormat.parse(time_expire);
                    return true;

                } catch (ParseException e) {
                    log.error("time_expire 不符合要求");
                    return false;
                }
            }else{
                log.error("time_expire 不符合要求");
                return false;
            }
        }else{
            return true;
        }
    }

    // goods_tag
    public boolean goods_tagValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        String goods_tag = orderReceivedRequestParameters.getGoods_tag();
        if(goods_tag != null){
            if(goods_tag.length() >=1 && goods_tag.length() <= 32){
                return true;
            }else{
                log.error("goods_tag 不符合要求");
                return false;
            }
        }else{
            return true;
        }
    }

    // amount
    public boolean amountValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        Amount amount = orderReceivedRequestParameters.getAmount();
        if(amount != null){
            if(amount.getTotal() != null && amount.getTotal() > 0){
                return true;
            }else{
                log.error("amount 不符合要求");
                return false;
            }
        }else{
            log.error("amount 不符合要求");
            return false;
        }
    }

    // detail
    public boolean detailValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        Detail detail = orderReceivedRequestParameters.getDetail();
        if (detail != null){
            if((detail.getCost_price() == null || detail.getCost_price() != null && detail.getCost_price() > 0) &&
                    goods_detailValidation(orderReceivedRequestParameters) &&
                    (detail.getInvoice_id() == null || (detail.getInvoice_id() !=null && detail.getInvoice_id().length() >= 1 &&
                            detail.getInvoice_id().length() <=32))){
                return true;
            }else{
                log.error("detail 不符合要求");
                return false;
            }
        }else{
            return true;
        }
    }

    // goods_detail
    public boolean goods_detailValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        Detail detail = orderReceivedRequestParameters.getDetail();
        if(detail.getGoods_detail() == null){
            return true;
        }else{
            boolean outCome = true;
            for (Goods_detail goods_detail : detail.getGoods_detail()) {
                if(goods_detail.getMerchant_goods_id() !=null &&
                        goods_detail.getMerchant_goods_id().length() >=1 &&
                        goods_detail.getMerchant_goods_id().length() <=32 &&
                        goods_detail.getQuantity() != null &&
                        goods_detail.getQuantity() > 0 &&
                        goods_detail.getUnit_price() != null &&
                        goods_detail.getUnit_price() > 0 &&
                        (goods_detail.getWechatpay_goods_id() == null || goods_detail.getWechatpay_goods_id() != null &&
                                goods_detail.getWechatpay_goods_id().length() >=1 &&
                                goods_detail.getWechatpay_goods_id().length() <= 32) &&
                        (goods_detail.getGoods_name() == null || goods_detail.getGoods_name() != null &&
                                goods_detail.getGoods_name().length() >= 1 &&
                                goods_detail.getGoods_name().length() <= 256)){

                }else{
                    log.error("goods_detail 不符合要求");
                    outCome = false;
                    break;
                }
            }
            return outCome;
        }
    }

    // scene_info
    public boolean scene_infoValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        Scene_info scene_info = orderReceivedRequestParameters.getScene_info();
        if(scene_info != null){
            if(scene_info.getPayer_client_ip() != null &&
                    scene_info.getPayer_client_ip().length() >= 1 &&
                    scene_info.getPayer_client_ip().length() <= 45 &&
                    (scene_info.getDevice_id() == null || scene_info.getDevice_id() != null &&
                            scene_info.getDevice_id().length() >= 1 &&
                            scene_info.getDevice_id().length() <= 32) &&
                    store_infoValidation(orderReceivedRequestParameters)){
                return true;
            }else{
                log.error("scene_info 不符合要求");
                return false;
            }
        }else{
            return true;
        }
    }

    // store_info
    public boolean store_infoValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        Scene_info scene_info = orderReceivedRequestParameters.getScene_info();
        if(scene_info.getStore_info() == null){
            return true;
        }else{
            if(scene_info.getStore_info().getId() != null &&
                    scene_info.getStore_info().getId().length() >= 1 &&
                    scene_info.getStore_info().getId().length() <= 32 &&
                    (scene_info.getStore_info().getName() == null || scene_info.getStore_info().getName() != null &&
                            scene_info.getStore_info().getName().length() >= 1 &&
                            scene_info.getStore_info().getName().length() <=256) &&
                    (scene_info.getStore_info().getArea_code() == null || scene_info.getStore_info().getArea_code() != null &&
                            scene_info.getStore_info().getArea_code().length() >= 1 &&
                            scene_info.getStore_info().getArea_code().length() <= 32) &&
                    (scene_info.getStore_info().getAddress() == null || scene_info.getStore_info().getAddress() != null &&
                            scene_info.getStore_info().getAddress().length() >= 1 &&
                            scene_info.getStore_info().getAddress().length() <= 512)
            ){
                return true;
            }else{
                log.error("store_info 不符合要求");
                return false;
            }
        }
    }
    // settle_info
    public boolean settle_infoValidation(OrderReceivedRequestParameters orderReceivedRequestParameters){
        return true;
    }
}
