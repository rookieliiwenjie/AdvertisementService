package com.imooc.ad.constant;

import lombok.Getter;

/**
 * Created by lwj32 on 2021/2/22.
 */
@Getter
public enum ConstantsCode {

    REQUEST_PARAM_ERROR("REQUEST_PARAM_ERROR", "请求参数错误"),
    SAME_NAME_ERROR("REQUEST_PARAM_ERROR", "存在同名的用户"),
    CAN_NOT_FIND_RECORD("CAN_NOT_FIND_RECORD", "找不到数据记录"),
    SAME_NAME_PLAN_ERROR("SAME_NAME_PLAN_ERROR", "存在同名的推广计划"),
    SAME_NAME_UNIT_ERROR("SAME_NAME_UNIT_ERROR", "存在同名的推广单元"),
    ERROR("Fail", "网络错误，请联系开发");
    private String code;
    private String desc;

    ConstantsCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ConstantsCode getByCode(String code) {
        for (ConstantsCode value : ConstantsCode.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return ERROR;
    }
}
