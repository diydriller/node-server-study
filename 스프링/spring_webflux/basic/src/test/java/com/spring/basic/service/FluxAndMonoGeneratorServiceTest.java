package com.spring.basic.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService=new FluxAndMonoGeneratorService();

    @Test
    void namesFlux(){
        var namesFlux=fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("alex")
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    void namesFluxMap(){
        var namesFluxMap=fluxAndMonoGeneratorService.namesFluxMap(3);

        StepVerifier.create(namesFluxMap)
                .expectNext("ALEX")
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap(){
        var namesFluxFlatMap=fluxAndMonoGeneratorService.namesFluxFlatMap();

        StepVerifier.create(namesFluxFlatMap)
                .expectNextCount(7)
                .verifyComplete();
    }
    @Test
    void namesFluxConcatMap(){
        var namesFluxConcatMap=fluxAndMonoGeneratorService.namesFluxConcatMap();

        StepVerifier.create(namesFluxConcatMap)
                .expectNextCount(7)
                .verifyComplete();
    }
    @Test
    void nameMonoFlatMap(){
        var nameMonoFlatMap=fluxAndMonoGeneratorService.nameMonoFlatMap();

        StepVerifier.create(nameMonoFlatMap)
                .expectNext(List.of("a","l","e","x"))
                .verifyComplete();
    }
    @Test
    void nameMonoFlatMapMany(){
        var nameMonoFlatMapMany=fluxAndMonoGeneratorService.nameMonoFlatMapMany();

        StepVerifier.create(nameMonoFlatMapMany)
                .expectNextCount(4)
                .verifyComplete();
    }
    @Test
    void namesFluxMapTransform(){
        var namesFluxMapTransform=fluxAndMonoGeneratorService.namesFluxMapTransform(3);

        StepVerifier.create(namesFluxMapTransform)
                .expectNext("ALEX")
                .expectNextCount(0)
                .verifyComplete();
    }
    @Test
    void namesFluxMapTransform_1(){
        var namesFluxMapTransform_1=fluxAndMonoGeneratorService.namesFluxMapTransform_1(6);

        StepVerifier.create(namesFluxMapTransform_1)
                .expectNext("default")
                .verifyComplete();
    }
    @Test
    void namesFluxMapTransform_2(){
        var namesFluxMapTransform_2=fluxAndMonoGeneratorService.namesFluxMapTransform_2(6);

        StepVerifier.create(namesFluxMapTransform_2)
                .expectNext("DEFAULT")
                .verifyComplete();
    }
    @Test
    void fluxConcat(){
        var fluxConcat=fluxAndMonoGeneratorService.fluxConcat();

        StepVerifier.create(fluxConcat)
                .expectNextCount(6)
                .verifyComplete();
    }
}