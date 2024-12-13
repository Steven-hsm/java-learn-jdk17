package com.hsm.learn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServer {

    public static void main(String[] args) {
        //1. 启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                //2. BossEventLoop,workEventLoop(selector,thread) group 组
                .group(new NioEventLoopGroup())
                //3. 选择服务器的 ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                //4. boss 负责处理连接worker(child) 负责处理读写，决定了work（child）能执行哪些操作
                .childHandler(
                        //5.channel 代表和客户端进行数据读写的通道Initializer初始化，负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>(){
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //6.添加具体的handler
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                //7.打印上一步转换好的字符串
                                System.out.println(msg);
                            }
                        });
                    }
                })
                //绑定监听端口
                .bind(8080);
    }
}
