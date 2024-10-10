package com.hsm.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class FluxMain {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 45, 0, 8).doOnNext(value -> {
            System.out.println("一起玩" + value + Thread.currentThread());
        }).subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("绑定成功" + Thread.currentThread());
                request(1); // 向发布者请求 1 次数据，n 表示请求 n 次数据
                // requestUnbounded(); // 请求无限次数据，用了该方法则 onNext 中无需再写 request(1)
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("当前数据:" + value + Thread.currentThread());
                if (value == 45) {
                    cancel(); // 取消流
                }
                request(1); // 继续要数据
            }
        });
    }
}
