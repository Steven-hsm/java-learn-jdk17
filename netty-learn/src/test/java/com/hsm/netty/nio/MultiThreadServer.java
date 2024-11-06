package com.hsm.netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.hsm.netty.bytebuffer.ByteBufferUtil.debugRead;

@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));

        Selector bossSelector = Selector.open();
        SelectionKey bossKey = serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);

        Worker worker = new Worker("worker-0");
        while (true){
            bossSelector.select();
            Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    log.debug("connected...{}" , socketChannel.getRemoteAddress());
                    worker.registerChannel(socketChannel);
                }
            }
        }


    }

    static class Worker implements Runnable{
        private Selector worker;
        private String name;
        private volatile boolean start;
        private ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();

        public Worker(String name) throws IOException {
            this.name = name;
            worker = Selector.open();
        }

        public void registerChannel(SocketChannel socketChannel) throws ClosedChannelException {

            if(!start){
                start = true;
                new Thread(this, name).start();
            }

            queue.add(()->{
                try {
                    socketChannel.register(worker, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });
            worker.wakeup();
        }

        @Override
        public void run() {
            while (start){
                try {
                    worker.select();
                    Runnable task = queue.poll();
                    if(task != null){
                        task.run();
                    }

                    Iterator<SelectionKey> iterator = worker.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if(key.isReadable()){
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel)key.channel();
                            // 读数据
                            channel.read(buffer);
                            buffer.flip();
                            debugRead(buffer);
                        }
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
