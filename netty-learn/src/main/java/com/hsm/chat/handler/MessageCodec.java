package com.hsm.chat.handler;

import com.hsm.chat.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {

    @Override
    public void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //1. 4字节的魔数
        out.writeBytes(new byte[]{1,2,3,4});
        //2. 1字节的版本
        out.writeByte(1);
        //3. 1字节的序列化方式 jdk 0，json 1
        out.writeByte(0);
        //4. 1字节的指令类型
        out.writeByte(msg.getMessageType());
        //5. 4个字节
        out.writeInt(msg.getSequenceId());
        //无意义，对其填充
        out.writeByte(0xff);
        //6. 获取内容的字节数组
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(msg);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        //7. 长度
        out.writeInt(byteArray.length);
        //8. 写入内容
        out.writeBytes(byteArray);
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializerType = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        if(serializerType == 0){
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Message message = (Message)objectInputStream.readObject();
            out.add(message);
            System.out.println(magicNum + "," + version + "," + serializerType + "," + messageType + "," + sequenceId + "," + length);
            System.out.println(message);
        }

    }
}
