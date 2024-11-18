package com.hsm.chatinaction.server.service;

/**
 * className: HelloServiceImpl
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
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String msg) {
        int i = 1 / 0;
        return "你好, " + msg;
    }
}