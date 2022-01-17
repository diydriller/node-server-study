package com.spring.web.controller;

import com.spring.web.dto.SimpleResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping
    public SimpleResponseDto checkCors(){
        return new SimpleResponseDto("success");
    }
}
