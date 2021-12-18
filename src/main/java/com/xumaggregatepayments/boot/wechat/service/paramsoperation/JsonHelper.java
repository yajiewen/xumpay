package com.xumaggregatepayments.boot.wechat.service.paramsoperation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-59-19
 * @Description
*/ 
import java.nio.charset.Charset;

@Service
public class JsonHelper {
    @Autowired
    private ObjectMapper objectMapper;

    public String objectToJson(Object object)  {
        byte[] bodyBytes = null;

        try {
            bodyBytes =  objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new String(bodyBytes, Charset.forName("UTF-8"));
    }

    public <T> T jsonToObject(String jsonString, Class<T> objclass){

        try {
           T  object = objectMapper.readValue(jsonString,objclass);
            return object;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
