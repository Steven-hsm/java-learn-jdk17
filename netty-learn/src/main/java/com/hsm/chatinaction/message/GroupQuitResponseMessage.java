package com.hsm.chatinaction.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * className: GroupQuitResponseMessage
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
public class GroupQuitResponseMessage extends AbstractResponseMessage {
    public GroupQuitResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return GROUP_QUIT_RESPONSE_MESSAGE;
    }
}
