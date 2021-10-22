package com.spring.redis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.redis.config.aop.RedisSessionCheck;
import com.spring.redis.config.aop.RedisSessionSet;
import com.spring.redis.dto.CreateUserRequestDto;
import com.spring.redis.model.User;
import com.spring.redis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class RedisSessionController {


    private final UserRepository userRepository;

    @PostMapping("/session")
    @RedisSessionSet
    public ResponseEntity<String> setSession(@RequestBody CreateUserRequestDto requestDto
            , HttpSession session){

        

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

    @GetMapping("/session/{sessionId}")
    @RedisSessionCheck
    public ResponseEntity<User> getSession(HttpSession session,
                                           @PathVariable("sessionId") String sessionId)
            throws Exception {

        String userJson = (String) session.getAttribute("login_" + sessionId);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }
}
