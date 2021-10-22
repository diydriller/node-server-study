package com.spring.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void setStringOps(String k, String v, Long ttl, TimeUnit unit){
        redisTemplate.opsForValue().set(k,v,ttl,unit);
    }

    public String getStringOps(String k){
        return (String)redisTemplate.opsForValue().get(k);
    }

    public void setListOps(String k, List<String> vList){
        redisTemplate.opsForList().rightPushAll(k,vList);
    }

    public List getListOps(String k){
        Long len=redisTemplate.opsForList().size(k);
        return len==0?new ArrayList():redisTemplate.opsForList().range(k,0,len-1);
    }
}
