package com.hsm.chatinaction.server.session;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * className: SessionMemoryImpl
 * description:
 * date: 2024/6/13-8:14
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.session
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public class SessionMemoryImpl implements Session {

    private final Map<String, Channel> usernameChannelMap = new ConcurrentHashMap<>();
    private final Map<Channel, String> channelUsernameMap = new ConcurrentHashMap<>();
    private final Map<Channel, Map<String, Object>> channelAttributesMap = new ConcurrentHashMap<>();

    /**
     * 绑定 会话
     *
     * @param channel  {@link Channel}
     * @param username {@link String}
     */
    @Override
    public void bind(Channel channel, String username) {
        usernameChannelMap.put(username, channel);
        channelUsernameMap.put(channel, username);
        channelAttributesMap.put(channel, new ConcurrentHashMap<>());
    }

    /**
     * 解绑 会话
     *
     * @param channel {@link Channel}
     */
    @Override
    public void unbind(Channel channel) {
        String username = channelUsernameMap.remove(channel);
        if (username != null) {
            usernameChannelMap.remove(username);
            channelAttributesMap.remove(channel);
        }
    }

    /**
     * 获取会话属性
     *
     * @param channel {@link Channel}
     * @param name    {@link String}
     * @return {@link Object}
     */
    @Override
    public Object getAttribute(Channel channel, String name) {
        return channelAttributesMap.get(channel).get(name);
    }

    /**
     * 设置会话属性
     *
     * @param channel {@link Channel}
     * @param name    {@link String}
     * @param value   {@link Object}
     */
    @Override
    public void setAttribute(Channel channel, String name, Object value) {
        channelAttributesMap.get(channel).put(name, value);
    }

    /**
     * 获取会话
     *
     * @param username {@link String}
     * @return {@link Channel}
     */
    @Override
    public Channel getChannel(String username) {
        return usernameChannelMap.get(username);
    }

    @Override
    public String toString() {
        return usernameChannelMap.toString();
    }
}
