package com.hsm.learn.eventLoop;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestPromise {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. 准备EventLoop 对象
        EventLoop eventLoop = new NioEventLoopGroup().next();

        //2. 可以主动创建promise结果对象
        DefaultPromise<Object> promise = new DefaultPromise<>(eventLoop);

        new Thread(() ->{
            System.out.println("开始计算");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();
        System.out.println("拿到计算结果" + promise.get());
    }
}
