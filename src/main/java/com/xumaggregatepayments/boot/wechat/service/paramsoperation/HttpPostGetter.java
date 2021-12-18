package com.xumaggregatepayments.boot.wechat.service.paramsoperation;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;
/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-19
 * @Description 获取HttpPost 类对象
*/ 
@Service
public class HttpPostGetter {

    public HttpPost getHttpPost(String url , String entityString){
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(entityString, "UTF-8"));
        return httpPost;
    }
}
