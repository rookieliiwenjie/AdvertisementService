package com.imooc.ad.tool;

import com.imooc.ad.enumContent.ErrorEnum;
import com.imooc.ad.vo.CommonResponse;
import com.imooc.ad.constant.ConstantsCode;
/**
 * Created by lwj32 on 2021/1/31.
 */
public class CommonRequesetTool {

    public static CommonResponse setSuccess(CommonResponse commonResponse) {
        commonResponse = (null == commonResponse ? new CommonResponse<>() : commonResponse);
        commonResponse.setCode(ErrorEnum.SUCCESS.getCode());
        commonResponse.setMessage(ErrorEnum.SUCCESS.getMessage());
        commonResponse.setResult(ErrorEnum.SUCCESS.getCode());
        return commonResponse;

    }

    public static CommonResponse setSuccess(CommonResponse commonResponse, String code, String message) {
        commonResponse = (null == commonResponse ? new CommonResponse<>() : commonResponse);
        commonResponse.setCode(code);
        commonResponse.setResult(ErrorEnum.SUCCESS.getCode());
        commonResponse.setMessage(message);
        return commonResponse;

    }

    public static CommonResponse setSuccess(CommonResponse commonResponse, Object data, String code, String message) {
        commonResponse = (null == commonResponse ? new CommonResponse<>() : commonResponse);
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        commonResponse.setResult(ErrorEnum.SUCCESS.getCode());
        return commonResponse;

    }

    public static CommonResponse setError(CommonResponse commonResponse) {
        commonResponse = (null == commonResponse ? new CommonResponse<>() : commonResponse);
        commonResponse.setCode(ErrorEnum.FAIL.getCode());
        commonResponse.setMessage(ErrorEnum.FAIL.getMessage());
        commonResponse.setResult(ErrorEnum.FAIL.getCode());
        return commonResponse;

    }

    public static CommonResponse setError(CommonResponse commonResponse, String code, String message) {
        commonResponse = (null == commonResponse ? new CommonResponse<>() : commonResponse);
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        commonResponse.setResult(ErrorEnum.FAIL.getCode());
        return commonResponse;

    }

    public static CommonResponse setError(CommonResponse commonResponse, Object data, String code, String message) {
        commonResponse = (null == commonResponse ? new CommonResponse<>() : commonResponse);
        commonResponse.setCode(code);
        commonResponse.setData(data);
        commonResponse.setMessage(message);
        commonResponse.setResult(ErrorEnum.FAIL.getCode());
        return commonResponse;

    }



}
