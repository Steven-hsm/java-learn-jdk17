package com.hsm.chatinaction.server.session;

/**
 * className: SessionFactory
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
public abstract class SessionFactory {

    private static final Session SESSION = new SessionMemoryImpl();

    public static Session getSession() {
        return SESSION;
    }
}
