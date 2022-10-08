package com.example.springbootnettyclient.common;

/**
 * @ClassName StatusCode
 * @Description 统一响应模型1. 统一状态码的接口，所有状态码都需要实现它
 * @Auther tanyi
 * @Date 2022/9/29
 * @Version 1.0
 **/
public interface StatusCode {

    int getCode();
    String getMsg();
}
