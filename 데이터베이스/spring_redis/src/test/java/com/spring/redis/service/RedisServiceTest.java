package com.spring.redis.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisService redisService;


    @Test
    void RedisString(){
        redisService.setStringOps("key","value",60l, TimeUnit.SECONDS);
        System.out.println(redisService.getStringOps("key"));
    }

    @Test
    void RedisList(){
        redisService.setListOps("key", List.of("value1","value2"));

        List<String> res=redisService.getListOps("key");
        for (String el:res) {
            System.out.println(el);
        }
    }

}