package com.hsm.learn.eventLoop;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;

import java.util.concurrent.TimeUnit;

public class TestEventLoop {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(2);
        //获取可用的线程数
        System.out.println(NettyRuntime.availableProcessors());
        //获取下一个事件循环对象
        System.out.println(group.next());

        //执行普通任务
        group.next().submit( () ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
        });
        //执行定时任务
        group.next().scheduleAtFixedRate(() ->{
            System.out.println("delay");
        },0,1,TimeUnit.SECONDS);

        System.out.println("end");
    }
}
