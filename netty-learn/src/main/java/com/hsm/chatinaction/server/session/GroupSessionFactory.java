package com.hsm.chatinaction.server.session;

/**
 * className: GroupSessionFactory
 * description:
 * date: 2024/6/13-8:10
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.session
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public abstract class GroupSessionFactory {

    private static final GroupSession SESSION = new GroupSessionMemoryImpl();

    public static GroupSession getGroupSession() {
        return SESSION;
    }
}
