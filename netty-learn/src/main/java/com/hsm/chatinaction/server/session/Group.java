package com.hsm.chatinaction.server.session;

import lombok.Data;

import java.util.Collections;
import java.util.Set;

/**
 * className: Group
 * description: 聊天组，即聊天室
 * date: 2024/6/13-8:10
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.session
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
@Data
public class Group {
    /**
     * 聊天室名称
     */
    private String name;
    /**
     * 聊天室成员
     */
    private Set<String> members;

    public static final Group EMPTY_GROUP = new Group("empty", Collections.emptySet());

    public Group(String name, Set<String> members) {
        this.name = name;
        this.members = members;
    }
}
