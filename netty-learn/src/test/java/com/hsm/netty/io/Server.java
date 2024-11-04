package com.hsm.netty.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.hsm.netty.bytebuffer.ByteBufferUtil.debugRead;

@Slf4j
public class Server {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        
        List<SocketChannel> channelList = new ArrayList<>();
        while (true){
            log.debug("connecting...");
            //阻塞方法，线程停止运行
            SocketChannel socketChannel = serverSocketChannel.accept();
            log.debug("connected...");
            channelList.add(socketChannel);

            for (SocketChannel channel : channelList) {
                log.debug("before read...");
                //阻塞方法，线程停止运行
                channel.read(buffer);
                buffer.flip();
                debugRead(buffer);
                buffer.clear();
                log.debug("after read...");
            }
        }
        
        

    }
}
