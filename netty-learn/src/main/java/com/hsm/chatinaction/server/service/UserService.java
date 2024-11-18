package com.hsm.chatinaction.server.service;

/**
 * className: UserService
 * description:
 * date: 2024/6/13-8:06
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.service
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username {@link  String}
     * @param password {@link  String}
     * @return {@link  Boolean}
     */
    boolean login(String username, String password);
}
