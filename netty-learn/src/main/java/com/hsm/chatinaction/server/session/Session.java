package com.hsm.chatinaction.server.session;

import io.netty.channel.Channel;

/**
 * className: Session
 * description: 会话管理接口
 * date: 2024/6/13-8:10
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.session
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public interface Session {

    /**
     * 绑定 会话
     *
     * @param channel  {@link Channel}
     * @param username {@link String}
     */
    void bind(Channel channel, String username);

    /**
     * 解绑 会话
     *
     * @param channel {@link Channel}
     */
    void unbind(Channel channel);

    /**
     * 获取会话属性
     *
     * @param channel {@link Channel}
     * @param name    {@link String}
     * @return {@link Object}
     */
    Object getAttribute(Channel channel, String name);

    /**
     * 设置会话属性
     *
     * @param channel {@link Channel}
     * @param name    {@link String}
     * @param value   {@link Object}
     */
    void setAttribute(Channel channel, String name, Object value);

    /**
     * 获取会话
     *
     * @param username {@link String}
     * @return {@link Channel}
     */
    Channel getChannel(String username);
}
