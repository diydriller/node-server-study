package com.spring.basic.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    // publisher : onSubscribe - onNext - onComplete
    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("alex","ben"))
                .log();
    }
    public Mono<String> nameMono(){
        return Mono.just("alex")
                .log();
    }

    // transformation
    // map : one-to-one transformation , synchronous
    public Flux<String> namesFluxMap(int stringLength){
        return Flux.fromIterable(List.of("alex","ben"))
                // reactive stream is immutable
                .map(String::toUpperCase)
                .filter(s->s.length()>stringLength)
                .log();
    }
    // flatMap : one-to-many transformation , not guarantee order
    public Flux<String> namesFluxFlatMap(){
        return Flux.fromIterable(List.of("alex","ben"))
                .flatMap(s->splitString(s))
                .log();
    }
    public Mono<List<String>> nameMonoFlatMap(){
        return Mono.just("alex")
                .flatMap(this::splitStringMono)
                .log();
    }
    public Flux<String> nameMonoFlatMapMany(){
        return Mono.just("alex")
                .flatMapMany(this::splitString)
                .log();
    }
    // concatMap : similar to flatMap but concatMap guarantee order
    public Flux<String> namesFluxConcatMap(){
        return Flux.fromIterable(List.of("alex","ben"))
                .concatMap(s->splitStringWithDelay(s))
                .log();
    }
    // transform
    public Flux<String> namesFluxMapTransform(int stringLength){

        // functional interface
        Function<Flux<String>,Flux<String>> filterMap=name->name.map(String::toUpperCase)
                .filter(s->s.length()>stringLength);

        return Flux.fromIterable(List.of("alex","ben"))
                .transform(filterMap)
                .log();
    }
    public Flux<String> namesFluxMapTransform_1(int stringLength){

        Function<Flux<String>,Flux<String>> filterMap=name->name.map(String::toUpperCase)
                .filter(s->s.length()>stringLength);

        return Flux.fromIterable(List.of("alex","ben"))
                .transform(filterMap)
                .defaultIfEmpty("default")
                .log();
    }
    public Flux<String> namesFluxMapTransform_2(int stringLength){

        Function<Flux<String>,Flux<String>> filterMap=name->name.map(String::toUpperCase)
                .filter(s->s.length()>stringLength);

        var defaultFlux=Flux.just("default")
                .transform(filterMap);

        return Flux.fromIterable(List.of("alex","ben"))
                .transform(filterMap)
                .switchIfEmpty(defaultFlux)
                .log();
    }
    Flux<String> fluxConcat(){
        var abcFlux=Flux.just("a","b","c");
        var defFlux=Flux.just("d","e","f");

        return Flux.concat(abcFlux,defFlux).log();
    }


    public Mono<List<String>> splitStringMono(String s){
        var charArray=s.split("");
        return Mono.just(List.of(charArray));
    }
    public Flux<String> splitString(String s){
        var charArray=s.split("");
        return Flux.fromArray(charArray);
    }
    public Flux<String> splitStringWithDelay(String s){
        var charArray=s.split("");
        var delay=new Random().nextInt(1000);
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
    }



    public static void main(String[] args){
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService=new FluxAndMonoGeneratorService();

        // subscriber : request
        fluxAndMonoGeneratorService.namesFlux()
                // access element one by one
                .subscribe(name->{
                    System.out.println("flux name is "+name);
                });

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name->{
                    System.out.println("mono name is "+name);
                });



    }
}
