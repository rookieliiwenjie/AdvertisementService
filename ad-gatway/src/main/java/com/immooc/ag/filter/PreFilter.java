package com.immooc.ag.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by lwj32 on 2021/1/31.
 */
@Slf4j
@Component
public class PreFilter extends ZuulFilter {
    //拦截器类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
    //filter顺序
    @Override
    public int filterOrder() {
        return 0;
    }
    //是否执行
    @Override
    public boolean shouldFilter() {
        return true;
    }
    //
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = new RequestContext();
        requestContext.set("startTime",System.currentTimeMillis());
        return null;
    }
}
