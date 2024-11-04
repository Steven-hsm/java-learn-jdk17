package com.hsm.netty.bytebuffer;

import java.nio.ByteBuffer;

import static com.hsm.netty.bytebuffer.ByteBufferUtil.debugAll;

public class TestByteBufferExample {

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
        debugAll(source);
    }

    private static void split(ByteBuffer source) {
        //切换成读模式
        source.flip();
        //最后一个字符
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            //对字符进行遍历，只有遇到了\n表示是新消息，可
            if (source.get(i) == '\n') {
                System.out.println(i);
                //这里因为没有把总的数据取出来，所以需要减去上一个的位置
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，一直读到limit向 target 写
                debugAll(target);
                //这里把limit改回去
                source.limit(oldLimit);
            }
        }
        source.compact();
    }
}
