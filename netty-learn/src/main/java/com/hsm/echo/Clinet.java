package com.hsm.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Clinet {

    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new EchoClientHandler());
                    }
                })
                .connect(new InetSocketAddress("localhost" ,8080))
                .sync()
                .channel();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!"q".equalsIgnoreCase(line)){
            channel.writeAndFlush(line);
            line = scanner.nextLine();
        }


    }
}
