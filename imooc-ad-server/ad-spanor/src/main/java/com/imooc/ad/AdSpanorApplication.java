package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by lwj32 on 2021/2/6.
 */
@SpringBootApplication
@EnableEurekaClient
//监控
@EnableFeignClients
//断路器
@EnableCircuitBreaker
public class AdSpanorApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdSpanorApplication.class, args);
    }
}
