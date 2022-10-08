package com.example.springbootnettyclient.common;

import lombok.Data;

/**
 * @ClassName ResultVo
 * @Description 统一响应模型3. 统一返回包装类
 * @Auther tanyi
 * @Date 2022/9/29
 * @Version 1.0
 **/
@Data
public class ResultData<t> {
    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private t data;

    // 接口完成时间
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功返回
     * @param data
     * @param <t>
     * @return
     */
    public static <t> ResultData<t> success(t data) {
        ResultData<t> resultData = new ResultData<>();
        resultData.setCode(ResultCode.SUCCESS.getCode());
        resultData.setMsg(ResultCode.SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 失败返回
     * @param data
     * @param <t>
     * @return
     */
    public static <t> ResultData<t> fail(t data) {
        ResultData<t> resultData = new ResultData<>();
        resultData.setCode(ResultCode.FAILED.getCode());
        resultData.setMsg(ResultCode.FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 手动设置返回vo
     * @param code
     * @param msg
     * @param data
     */
    public static <t> ResultData<t> response(int code, String msg, t data) {
        ResultData<t> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);

        return resultData;
    }


















}
