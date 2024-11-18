package com.hsm.chatinaction.message;

import lombok.*;

/**
 * className: ChatRequestMessage
 * description:
 * date: 2024/6/12-8:05
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.protocol
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestMessage extends Message {
    private String content;
    private String to;
    private String from;

    @Override
    public int getMessageType() {
        return CHAT_REQUEST_MESSAGE;
    }
}
