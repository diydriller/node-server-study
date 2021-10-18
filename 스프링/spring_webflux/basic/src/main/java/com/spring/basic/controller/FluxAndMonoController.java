package com.spring.basic.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


import java.time.Duration;

@RestController
public class FluxAndMonoController {

    @GetMapping(value = "/flux",produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Integer> getIntegerFlux(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }


}
