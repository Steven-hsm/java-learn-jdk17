package com.hsm.chatinaction.protocol;

import com.hsm.chatinaction.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * className: TestMessageCodec
 * description:
 * date: 2024/6/12-12:15
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.protocol
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public class TestMessageCodec {
    public static void main(String[] args) throws Exception {

        // 工人1 1234
        // 工人2 1234
        LengthFieldBasedFrameDecoder frameDecoder = new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0);
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        EmbeddedChannel channel = new EmbeddedChannel(
                frameDecoder,
                loggingHandler,
                new MessageCodec()
        );
        // encode
        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123");
        // channel.writeOutbound(message);

        // decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);

        ByteBuf s1 = buf.slice(0, 100);
        ByteBuf s2 = buf.slice(100, buf.readableBytes() - 100);
        s1.retain();
        // release 1
        channel.writeInbound(s1);
        channel.writeInbound(s2);
    }
}
