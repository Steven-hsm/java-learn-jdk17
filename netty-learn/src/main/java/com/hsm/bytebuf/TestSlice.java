package com.hsm.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.hsm.bytebuf.TestByteBuf.log;

public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        buffer.writeBytes(new byte[]{1,2,3,4,5,6,7,8,9,10});
        log(buffer);

        //在切片的过程中，没有发生数据复制
        //切片后的数据，不能呢过扩容
        ByteBuf slice1 = buffer.slice(0, 5);
        log(slice1);
        ByteBuf slice2 = buffer.slice(5, 5);
        log(slice2);
    }

}
