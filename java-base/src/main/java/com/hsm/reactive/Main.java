package com.hsm.reactive;

import java.io.IOException;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 定义发布者：可发布消息
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()){

            // 2. 定义订阅者：订阅者可订阅发布者发布的消息
            Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
                // 保存绑定关系
                private Flow.Subscription subscription;
                // 绑定订阅消息时触发
                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    this.subscription = subscription;
                    System.out.println("订阅事件发生了");
                    subscription.request(1); // 背压模式，订阅者向发布者请求发布信息
                    System.out.println("订阅者线程：" + Thread.currentThread()); // Thread[ForkJoinPool.commonPool-worker-1,5,main]
                }

                @Override
                public void onNext(String item) {
                    System.out.println("本轮：" + item);
                    subscription.request(1);
                    if (item.equals("原材料0")) {
//						throw new RuntimeException("自控异常");
                    }
                    System.out.println("订阅者线程Next：" + Thread.currentThread()); // Thread[ForkJoinPool.commonPool-worker-1,5,main]
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("异常了：" + throwable.getMessage());
                }

                @Override
                public void onComplete() {
                    System.out.println("完成了");
                }
            };
            // 3. 发布者的订阅者列表中添加这名订阅者，后续发布信息会发送给订阅者
            publisher.subscribe(subscriber);

            // 4. 发布者发布消息
            for (int i = 0; i < 10; i++) {
                publisher.submit("原材料" + i);
            }

            System.out.println("主线程：" + Thread.currentThread()); // Thread[main,5,main]
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 10; i++) {
                publisher.submit("原材料" + i);
            }



            publisher.close(); // 这样才会回调 onComplete 方法

            System.in.read();
        }
    }
}
