package com.xumaggregatepayments.boot.wechat.service.paramsoperation;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

/**
 * @Author yajiewen
 * @Date 2021-10-28 15-40-37
 * @Description 获取HttpGet 类对象
*/
@Service
@Slf4j
public class HttpGetGetter {
    public HttpGet getHttpGet(String url){
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            log.error(e.toString());
        }
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            log.error(e.toString());
        }
        httpGet.addHeader("Accept", "application/json");

        return httpGet;
    }
}
