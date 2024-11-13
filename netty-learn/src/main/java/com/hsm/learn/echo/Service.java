package com.hsm.learn.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class Service {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(3);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.option(ChannelOption.SO_RCVBUF, 20);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new StringEncoder());

                    socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {

                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//                            ByteBuf buffer = socketChannel.alloc().buffer();
//                            buffer.writeBytes(s.getBytes());
                            channelHandlerContext.writeAndFlush(s);
                            System.out.println("=========server received: " + s);
                        }
                    });

                }
            });
            serverBootstrap.bind(8080).sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
