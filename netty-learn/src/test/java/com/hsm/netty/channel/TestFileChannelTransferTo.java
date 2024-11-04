package com.hsm.netty.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        String FROM = "data.txt";
        String TO = "to.txt";
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO).getChannel();
        ) {
            //效率高，底层利用操作系统的零拷贝进行优化，最多2G数据
            long size = from.size();
            for (long leave = size; leave > 0; ) {
                long uploadSize = from.transferTo(size - leave, leave, to);
                leave -= uploadSize;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("transferTo 用时：" + (end - start) / 1000_000.0);
    }
}
