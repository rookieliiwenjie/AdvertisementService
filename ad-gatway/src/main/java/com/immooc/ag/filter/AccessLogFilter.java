package com.immooc.ag.filter;

import com.immooc.ag.filter.model.RequestDTO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lwj32 on 2021/1/31.
 */
@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    //弱国shuldefilter为true那么执行，也就是他是拦截器执行的内容
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = new RequestContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        RequestDTO requestDTO = new RequestDTO();
        Long startTime = (Long) requestContext.get("startTime");
        Long time = System.currentTimeMillis() - startTime;
        requestDTO.setTime(time);
        requestDTO.setUrl(httpServletRequest.getRequestURI());
        log.info(requestDTO.toString());
        return null;
    }
}
