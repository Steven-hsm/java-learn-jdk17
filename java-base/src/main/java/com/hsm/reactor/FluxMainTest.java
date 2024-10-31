package com.hsm.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxMainTest {
    public static void main(String[] args) throws InterruptedException {
//        Flux.concat(Flux.just("1232test", "123test").delayElements(Duration.ofSeconds(5)),
//                Flux.just("1232", "123").delayElements(Duration.ofSeconds(5)))
//                        .subscribe(System.out::println);



        Flux.just(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
                .delayElements(Duration.ofSeconds(2))
                .timeout(Duration.ofSeconds(3))
                .retry(4)
                .log()
                .subscribe(System.out::println);
        Thread.sleep(330000);
    }
}
