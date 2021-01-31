package com.imooc.ad.enumContent;

import lombok.Data;
import lombok.Getter;

/**
 * Created by lwj32 on 2021/1/31.
 */
@Getter
public enum ErrorEnum {
    SUCCESS("success", "调用成功"),
    FAIL("fail", "调用失败");
    private String code;
    private String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
