package com.immooc.ag.filter.model;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lwj32 on 2021/1/31.
 */
@Slf4j
@Data
public class RequestDTO {
    //请求路径
    private String url;
    //请求时间
    private Long time;
    
}
