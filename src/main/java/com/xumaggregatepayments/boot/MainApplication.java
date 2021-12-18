package com.xumaggregatepayments.boot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author yajiewen
 * @Date 2021-10-27 13-55-27
 * @Description
*/
@SpringBootApplication

public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
    }
}
