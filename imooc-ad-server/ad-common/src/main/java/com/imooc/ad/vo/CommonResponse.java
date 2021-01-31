package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lwj32 on 2021/1/31.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {

    private String code;
    private String message;
    private T data;
    private String result;

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
