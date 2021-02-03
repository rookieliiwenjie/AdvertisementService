package com.immooc.ag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by lwj32 on 2021/1/30.
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableScheduling
public class ZuulGatWayApplication  {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatWayApplication.class,args);
    }
}
