package com.hsm.learn.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Clinet {

    public static void main(String[] args) {

        NioEventLoopGroup worker = new NioEventLoopGroup();
        Scanner in = new Scanner(System.in);
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    System.out.println("connecting...");

                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf in = (ByteBuf) msg;
                            int len = in.readableBytes();
                            byte[] arr = new byte[len];
                            in.getBytes(0, arr);
                            System.out.println("==================client received: " + new String(arr, "UTF-8"));
                            in.release();
                        }
                    });
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync();

            while(in.hasNext())
            {
                String s = in.nextLine();
                ByteBuf buffer = channelFuture.channel().alloc().buffer();
                buffer.writeBytes(s.getBytes());
                channelFuture.channel().writeAndFlush(buffer);
                if(s.equals("quit"))
                    break;
            }
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }
}
