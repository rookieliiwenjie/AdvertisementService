package com.imooc.ad.advice;

import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by lwj32 on 2021/1/31.
 * 拦截所有json
 */
@RestControllerAdvice
public class CommonResonseDataAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //类级别
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> commonResponse = new CommonResponse("success","");
        if(o==null){
            return commonResponse;
        }
        else if(o instanceof CommonResponse){
             commonResponse = (CommonResponse<Object>) o;
        }else {
             commonResponse.setData(o);
        }
        return commonResponse;
    }
}
