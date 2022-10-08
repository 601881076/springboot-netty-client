package com.example.springbootnettyclient;

import com.example.springbootnettyclient.netty.NettyClient;
import io.netty.channel.ChannelFuture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootNettyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyClientApplication.class, args);
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     ChannelFuture channelFuture = NettyClient.start();
    //
    //     // 这个语句的主要目的是，如果缺失上述代码，则main方法所在的线程，
    //     // 即主线程会在执行完bind().sync()方法后，会进入finally 代码块，之前的启动的nettyserver也会随之关闭掉，整个程序都结束了。
    //     // channelFuture.channel().closeFuture().sync();
    // }
}
