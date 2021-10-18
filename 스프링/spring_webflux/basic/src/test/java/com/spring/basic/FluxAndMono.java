package com.spring.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMono {

    @Test
    public void fluxTest(){
        Flux<String> flux=Flux.just("spring","webflux","spring boot")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .log();

        flux
                .subscribe(System.out::println,(e)->System.out.println("Exception is "+e)
                ,()->{System.out.println("Completed");});
    }
}
