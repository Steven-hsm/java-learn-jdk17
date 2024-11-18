package com.hsm.chatinaction.message;

import lombok.*;

/**
 * className: GroupChatRequestMessage
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
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class GroupChatRequestMessage extends Message {
    private String content;
    private String groupName;
    private String from;

    @Override
    public int getMessageType() {
        return GROUP_CHAT_REQUEST_MESSAGE;
    }
}
