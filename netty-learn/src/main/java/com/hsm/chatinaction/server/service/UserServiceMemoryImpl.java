package com.hsm.chatinaction.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * className: UserServiceMemoryImpl
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
public class UserServiceMemoryImpl implements UserService {

    private final Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }

    /**
     * 登录
     *
     * @param username {@link  String}
     * @param password {@link  String}
     * @return {@link  Boolean}
     */
    @Override
    public boolean login(String username, String password) {
        String pass = allUserMap.get(username);
        return pass != null && pass.equals(password);
    }
}
