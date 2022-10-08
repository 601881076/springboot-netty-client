package com.example.springbootnettyclient.controller;

import com.example.springbootnettyclient.common.ResultData;
import com.example.springbootnettyclient.netty.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName NettyClientController
 * @Description 客户端controller
 * @Auther tanyi
 * @Date 2022/9/25
 * @Version 1.0
 **/
@RestController
@RequestMapping("/client")
@Slf4j
public class NettyClientController {


    /**
     * @description: 模拟向服务器发送消息
     * @param
     * @Author: wuyong
     * @Date: 2019/08/30 14:10:09
     * @return: java.lang.String
     */
    @PostMapping("/req")
    public ResultData req(@RequestParam String msg) throws Exception {
        log.info("准备向netty服务器发送消息{}", msg);
        // String msg = "{\"msgType\":\"req\",\"clientId\":\"请求数据\"}";
        // NettyClient.getSocketChannel().writeAndFlush(msg);
        // return "success";

        return NettyClient.helloNetty(msg);

    }
}
