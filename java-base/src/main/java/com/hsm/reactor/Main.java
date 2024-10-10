package com.hsm.reactor;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Main {
    public static void main(String[] args) {
        Flux<Integer> just = Flux.just(1, 2, 3, 45, 0, 8);
        just.subscribe(System.out::println); // 逐个遍历打印
        just.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnNext (Integer value){
                System.out.println(String.valueOf(value) + Thread.currentThread()); // Thread[main,5,main]
            }
        });
        System.out.println(Thread.currentThread()); // Thread[main,5,ma
    }
}
