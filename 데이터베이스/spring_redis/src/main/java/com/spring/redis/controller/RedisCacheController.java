package com.spring.redis.controller;

import com.spring.redis.model.User;
import com.spring.redis.repository.UserRepository;
import com.spring.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class RedisCacheController {
    private final UserRepository userRepository;
    private final UserService userService;

    // 유저 조회
    @GetMapping("/user/{userId}")
    ResponseEntity<User> findUser(
            @PathVariable("userId") Long userId
    ) {
        long startTime=System.currentTimeMillis();

        User user=userRepository.findById(userId).get();

        long endTime=System.currentTimeMillis();
        System.out.println("process time : "+(endTime-startTime));


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    // 유저 캐시로 조회
    @GetMapping("cache/user/{userId}")
    ResponseEntity<User> findCacheUser(
            @PathVariable("userId") Long userId
    ) {
        long startTime=System.currentTimeMillis();

        User user=userService.findUser(userId).get();

        long endTime=System.currentTimeMillis();
        System.out.println("process time : "+(endTime-startTime));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }



}
