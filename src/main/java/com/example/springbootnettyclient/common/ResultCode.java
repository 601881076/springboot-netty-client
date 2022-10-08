package com.example.springbootnettyclient.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResultCode
 * @Description 统一响应模型2. 与前端约定状态码
 * 当然不能有 setter 方法了，因此我们不能在用 @Data 注解了，我们要用 @Getter。
 * @Auther tanyi
 * @Date 2022/9/29
 * @Version 1.0
 **/
@Getter
public enum ResultCode implements StatusCode{
    SUCCESS(1000, "请求成功"),
    FAILED(1001,"请求失败"),
    VALIDATE_ERROR(1002,"参数校验失败"),
    RESPONSE_PACK_ERROR(1003,"response返回包装失败 "),
    ;

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
