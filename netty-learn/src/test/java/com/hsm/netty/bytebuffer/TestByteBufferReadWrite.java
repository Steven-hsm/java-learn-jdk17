package com.hsm.netty.bytebuffer;

import java.nio.ByteBuffer;

import static com.hsm.netty.bytebuffer.ByteBufferUtil.debugAll;

public class TestByteBufferReadWrite {

    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(10);
        debugAll(allocate);
        allocate.put((byte) 0x61);
        debugAll(allocate);

        allocate.put(new byte[]{0x62, 0x63, 0x64});
        debugAll(allocate);

        allocate.flip();
        debugAll(allocate);

        allocate.flip();
        debugAll(allocate);

        //allocate.put(new byte[]{0x62, 0x63, 0x64,0x62, 0x63, 0x64});
        //debugAll(allocate);

    }
}
