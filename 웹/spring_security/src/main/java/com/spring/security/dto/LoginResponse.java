package com.spring.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

// 로그인 응답구조
@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String token;
}
