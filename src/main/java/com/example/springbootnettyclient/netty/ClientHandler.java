package com.example.springbootnettyclient.netty;

import com.example.springbootnettyclient.common.ResultData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ClientHandler
 * @Description 客户端handler
 * @Auther tanyi
 * @Date 2022/9/23
 * @Version 1.0
 **/
@Slf4j
@ChannelHandler.Sharable
@Data
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ResultData resultData;

    /**
     * 连接到服务器时触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务器已连接");

        //发送消息
        // for (int i = 0; i < 10; i++) {
        //     ctx.writeAndFlush(Unpooled.copiedBuffer("current time\n", CharsetUtil.UTF_8));
        // }

        String msg = "你好啊，客户端";
        // ctx.writeAndFlush(Unpooled.copiedBuffer("current time\n", CharsetUtil.UTF_8));

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务器已断开");
    }

    /**
     * 发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 消息到来时触发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // ByteBuf buf = (ByteBuf) msg;
        // String s = buf.toString(CharsetUtil.UTF_8);
        // log.info("客户端收到消息 {}", s);
        // this.resultData = ResultData.success(s);
        // log.info("current Time{}", s);
        log.info("客户端收到消息: {}", msg.toString());
        this.resultData = resultData.success(msg.toString());

        ctx.close();

    }
}
