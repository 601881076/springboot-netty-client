package com.example.springbootnettyclient.netty;

import com.example.springbootnettyclient.common.ResultData;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * @ClassName NettyClient
 * @Description netty客户端
 * @Auther tanyi
 * @Date 2022/9/23
 * @Version 1.0
 **/
@Slf4j
public class NettyClient {

    // 创建线程组
    private static final EventLoopGroup group = new NioEventLoopGroup();

    private static final String HOST = "127.0.0.1";
    private static final int port = 8081;
    // 全局通道连接
    private static SocketChannel socketChannel = null;

    // public static ResultData helloNetty(String msg) {
    //
    //     final ClientHandler clientHandler = new ClientHandler();
    //
    //     ChannelFuture channelFuture = null;
    //     try {
    //         Bootstrap bootstrap = new Bootstrap();
    //         bootstrap.group(group)
    //                 .channel(NioSocketChannel.class)
    //                 //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
    //                 .option(ChannelOption.TCP_NODELAY, true)
    //                 .handler(new ChannelInitializer<SocketChannel>() {
    //                     @Override
    //                     protected void initChannel(SocketChannel socketChannel) throws Exception {
    //                         // 使用netty自带的粘包处理器，注意顺序需要自定义业务前
    //                         socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
    //                         socketChannel.pipeline().addLast(new StringEncoder());
    //                         socketChannel.pipeline().addLast(new StringDecoder());
    //
    //                         // 自定义处理程序
    //                         socketChannel.pipeline().addLast(clientHandler);
    //                     }
    //                 });
    //
    //         // 绑定端口并同步等待
    //         channelFuture = bootstrap.connect(HOST, port).sync();
    //
    //         if (channelFuture.isSuccess()) {
    //             socketChannel = (SocketChannel) channelFuture.channel();
    //             log.info("连接服务器成功...");
    //         }
    //
    //         // 发送消息
    //         log.info("开始发送消息...");
    //         channelFuture.channel().writeAndFlush(msg);
    //         // 等待通道被关闭
    //         channelFuture.channel().closeFuture().sync();
    //
    //         return clientHandler.getResultData();
    //
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     } finally {
    //         group.shutdownGracefully();
    //     }
    //
    // }

    public static ResultData helloNetty(String msg) throws Exception {
        ClientHandler clientHandler = new ClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("decoder", new StringDecoder());
                        socketChannel.pipeline().addLast("encoder", new StringEncoder());
                        socketChannel.pipeline().addLast(clientHandler);

                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(HOST, port).sync();

            log.info("客户端发送成功....");
            future.channel().writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));

            //发送消息
            // future.channel().writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));

            // 等待连接被关闭
            future.channel().closeFuture().sync();

            return clientHandler.getResultData();
        } catch (Exception e) {
            log.error("客户端Netty失败", e);
            throw new Exception("客户端Netty失败");
        } finally {
            //以一种优雅的方式进行线程退出
            group.shutdownGracefully();
        }


    }

    public static SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
