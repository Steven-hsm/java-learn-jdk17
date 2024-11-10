package com.hsm.eventLoop;

import java.util.concurrent.*;

public class TestJdkFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        });
        System.out.println(future.get());

    }
}
