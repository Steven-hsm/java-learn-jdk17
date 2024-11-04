package com.hsm.netty.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        //FileChannel
        //1. 输入输出流
        //2. RandomAccessFile

        try(FileChannel channel = new FileInputStream("data.txt").getChannel()){
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(2);

            while(true){
                //从channel读取数据，向buffer写入
                int len = channel.read(buffer);
                if(len == -1){
                    break;
                }
                //打印buffer的内容
                buffer.flip();//切换至读模式
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    log.error("读取到的内容：{}" ,(char) b);
                }
                buffer.flip();
            }


        }catch (Exception ignored){

        }
    }
}
