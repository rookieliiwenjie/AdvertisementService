package com.immooc.ag.interceptor;

import org.aopalliance.intercept.Interceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by lwj32 on 2021/1/30.
 */
@Component
@SpringBootApplication
public class LogInterface implements HandlerInterceptor {
}
