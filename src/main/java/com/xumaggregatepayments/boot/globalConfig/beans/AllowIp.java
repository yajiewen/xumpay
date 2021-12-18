package com.xumaggregatepayments.boot.globalConfig.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author yajiewen
 * @Date 2021-11-16 12-02-59
 * @Description
*/
@Component
@Data
@ConfigurationProperties(prefix = "allowhost")
public class AllowIp {
    private ArrayList<String> ip;
}
