package com.spring.redis.service;

import com.spring.redis.model.User;
import com.spring.redis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    @Cacheable(key = "#userId",value="findUser")
    public Optional<User> findUser(Long userId){
        return userRepository.findById(userId);
    }
}
