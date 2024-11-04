package com.hsm.netty.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.hsm.netty.bytebuffer.ByteBufferUtil.debugAll;

@Slf4j
public class TestByteBufferString {
    public static void main(String[] args) {
        //1.字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        //2. Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);

        //3.wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer2);

        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        log.error("str1:{}" ,str1);




    }
}
