package com.example.platform.uilt;

import lombok.Getter;

@Getter
public enum SystemErrorType {
    SYSTEM_ERROR("500", "系统异常"),

    SYSTEM_BUSY("000003", "系统繁忙,请稍候再试"),

    SUCCESS("200","处理成功"),

    PARAM_INVALID("401", "请求参数异常！"),

    PARAM_LABEL_INVALID("405", "巡检项已存在！"),

    PRODUCT_LABEL_INVALID("409", "设备类型已经注册！"),

    IOT_SYSTEM_ERROR("5000", "iot系统异常");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
