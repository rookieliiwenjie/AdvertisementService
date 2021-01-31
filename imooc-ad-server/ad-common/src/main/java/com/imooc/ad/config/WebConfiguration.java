package com.imooc.ad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by lwj32 on 2021/1/31.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    //消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.clear();
            converters.add(new MappingJackson2HttpMessageConverter());
    }
}
