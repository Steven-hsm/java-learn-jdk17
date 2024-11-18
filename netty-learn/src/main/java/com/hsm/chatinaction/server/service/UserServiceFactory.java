package com.hsm.chatinaction.server.service;

import lombok.Getter;

/**
 * className: UserServiceFactory
 * description:
 * date: 2024/6/13-8:08
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.service
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public abstract class UserServiceFactory {

    @Getter
    private static final UserService userService = new UserServiceMemoryImpl();

}
