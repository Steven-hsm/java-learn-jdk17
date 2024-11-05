package com.hsm.netty.io;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.hsm.netty.bytebuffer.ByteBufferUtil.debugRead;

@Slf4j
public class SelectorServer {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1. 创建selector，管理多个channel
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        //2.建立selctor和channel的联系（注册）
        //selectionKey就是将来事件发生后，通过它可以知道事件和哪个channel的事件
        //accept 会在连接请求时触发
        //connect 是客户端，连接建立后触发
        //read 可读事件
        //write 可写事件
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, null);
        //selectionKey 只关注 accept事件
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
        while (true) {
            //3、select方法，没有事件发生，线程阻塞，有事件，线程才会恢复运行
            //select 在事件未处理时，它不会阻塞，事件发生后，要么取消，要么处理，不能置之不理
            int select = selector.select();
            //4.处理事件，selectionKeys内部包含了所有的事件,发生事件后，加入key，但是不会删除
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //处理玩之后，删除selectionKeys
                iterator.remove();
                log.error("key:{}", key);

                //5.区分事件类型
                if(key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    SelectionKey socketSelectorKey = socketChannel.register(selector, 0, null);
                    socketSelectorKey.interestOps(SelectionKey.OP_READ);

                    log.error("{}", socketChannel);
                }else if(key.isReadable()){
                    try{
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        ByteBuffer socketByteBuffer = ByteBuffer.allocate(16);
                        //如果是正常断开，read的方法的返回值是-1
                        int read = socketChannel.read(socketByteBuffer);
                        if(read == -1){
                            key.cancel();
                        }else{
                            socketByteBuffer.flip();
                            debugRead(socketByteBuffer);
                        }
                    } catch (Exception e) {
                        //客户端关闭了，并没有处理select事件，所以需要手动取消
                        log.error("报错了",e);
                        key.cancel();
                    }

                }


            }
        }


    }
}
