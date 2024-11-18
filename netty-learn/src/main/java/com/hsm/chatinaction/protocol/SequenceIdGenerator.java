package com.hsm.chatinaction.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * className: SequenceIdGenerator
 * description:
 * date: 2024/6/12-14:05
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.protocol
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public abstract class SequenceIdGenerator {
    private static final AtomicInteger ID = new AtomicInteger();

    public static int nextId() {
        return ID.incrementAndGet();
    }
}
