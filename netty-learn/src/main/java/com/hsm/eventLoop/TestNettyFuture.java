package com.hsm.eventLoop;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TestNettyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Future<Integer> future = group.next().submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        });

        //System.out.println(future.get());

        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                System.out.println("拿到结果:" + future.getNow());
            }
        });
    }
}
