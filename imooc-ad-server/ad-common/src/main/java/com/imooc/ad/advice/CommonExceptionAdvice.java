package com.imooc.ad.advice;

import com.imooc.ad.constant.ConstantsCode;
import com.imooc.ad.enumContent.ErrorEnum;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.tool.CommonRequesetTool;
import com.imooc.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lwj32 on 2021/1/31.
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
    @ExceptionHandler({AdException.class})
    public CommonResponse exceptionHandler(HttpServletRequest httpServletRequest, AdException e) {
        ConstantsCode constantsCode = ConstantsCode.getByCode(e.getMessage());
        CommonResponse commonResponse = CommonRequesetTool.setError(new CommonResponse(), constantsCode.getCode(), constantsCode.getDesc());
        log.error("请求失败", httpServletRequest.getRequestURI() + "发生异常" + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler({Exception.class})
    public CommonResponse exceptionHandlerE(HttpServletRequest httpServletRequest, Exception e) {

        CommonResponse commonResponse = CommonRequesetTool.setError(new CommonResponse(), ErrorEnum.FAIL.getCode(), e.getMessage());
        log.error("请求失败", httpServletRequest.getRequestURI() + "发生异常" + e.getMessage());
        return commonResponse;
    }
}
