package com.hsm.chatinaction.message;

import lombok.*;

/**
 * className: LoginRequestMessage
 * description:
 * date: 2024/6/12-8:12
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.message
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
public class LoginRequestMessage extends Message {
    private String username;
    private String password;

    /**
     * 获取消息列
     *
     * @return {@link Integer}
     */
    @Override
    public int getMessageType() {
        return LOGIN_REQUEST_MESSAGE;
    }

}
