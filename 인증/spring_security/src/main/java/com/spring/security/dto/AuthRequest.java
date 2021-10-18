package com.spring.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

// 회원가입 , 로그인 요청구조
@Data
@AllArgsConstructor
public class AuthRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
