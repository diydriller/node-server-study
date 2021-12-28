package com.spring.redis.controller;

import com.spring.redis.config.aop.RedisSessionCheck;
import com.spring.redis.dto.CreatePostRequestDto;
import com.spring.redis.dto.CreateUserRequestDto;
import com.spring.redis.model.Post;
import com.spring.redis.model.User;
import com.spring.redis.model.UserDto;
import com.spring.redis.repository.PostRepository;
import com.spring.redis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class RedisSessionController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // user 생성후 user 응답
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDto requestDto){

        User user=new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        User savedUser=userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedUser);
    }

    // 세션 생성후 success 문자열 응답
    @GetMapping("user/{userId}/session")
    public ResponseEntity<String> setSession(HttpSession session,@PathVariable("userId") Long userId) throws IllegalAccessException {

        // redis에 string으로 저장
//        ObjectMapper objectMapper=new ObjectMapper();
//        String userJson=objectMapper.writeValueAsString(savedUser);

        User user=userRepository.findById(userId).get();

        session.setAttribute("login_"+user.getId(), UserDto.toUserDto(user));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }


    // 세션 조회후 user 응답
    @GetMapping("/session/{sessionId}/user")
    @RedisSessionCheck
    public ResponseEntity<User> getUser(HttpSession session, @PathVariable("sessionId") String sessionId){

        // redis에서 string으로 꺼내서 user로 매핑
//        String userJson = (String) session.getAttribute("login_" + sessionId);
//        ObjectMapper objectMapper = new ObjectMapper();
//        User user = objectMapper.readValue(userJson, User.class);

        User user = User.toUser((UserDto) session.getAttribute("login_"+sessionId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }


    // 세션 조회후 post 생성후 post 응답
    @PostMapping("/session/{sessionId}/post")
    @RedisSessionCheck
    public ResponseEntity<Post> createPost(HttpSession session, @PathVariable("sessionId") Long sessionId,
                                           @RequestBody  CreatePostRequestDto requestDto){

        User user = User.toUser((UserDto) session.getAttribute("login_"+sessionId));
        //User user=userRepository.findById(sessionId).get();

        Post post=new Post();
        post.setContent(requestDto.getContent());
        post.setTitle(requestDto.getTitle());
        post.setUser(user);


        Post savedPost=postRepository.save(post);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedPost);
    }


}
