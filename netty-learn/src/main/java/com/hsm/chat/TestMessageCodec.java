package com.hsm.chat;

import com.hsm.chat.handler.MessageCodec;
import com.hsm.chat.message.LoginMessage;
import com.hsm.chat.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024,12,4,0,0),
                new LoggingHandler(),
                new MessageCodec());
        //encode
        LoginMessage loginMessage = new LoginMessage("张三", "1234567");
        embeddedChannel.writeOutbound(loginMessage);

        //decode
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, loginMessage, buffer);

        //入站
        embeddedChannel.writeInbound(buffer.duplicate());
    }
}
