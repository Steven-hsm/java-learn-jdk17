package com.hsm.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class HelloClient {

    public static void main(String[] args) throws InterruptedException {
        //1. 启动器，负责组装netty组件，启动服务器
        new Bootstrap()
                //2. BossEventLoop,workEventLoop(selector,thread) group 组
                .group(new NioEventLoopGroup())
                //3. 选择服务器的 ServerSocketChannel实现
                .channel(NioSocketChannel.class)
                //4. 添加处理器
                .handler(
                        //5.channel 代表和客户端进行数据读写的通道Initializer初始化，负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>(){
                    @Override //连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //6.添加具体的handler
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                //5.连接到服务器
                .connect(new InetSocketAddress("localhost",8080))
                .sync()
                .channel()
                //向服务器发送数据
                .writeAndFlush("hello world");
    }
}
