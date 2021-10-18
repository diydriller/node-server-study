package com.spring.security.controller;



import com.spring.security.dto.ApiUtils.*;
import com.spring.security.dto.AuthRequest;

import com.spring.security.dto.JoinResponse;
import com.spring.security.error.NotFoundException;
import com.spring.security.model.AuthUser;
import com.spring.security.repository.AuthRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring.security.dto.ApiUtils.error;
import static com.spring.security.dto.ApiUtils.success;


@RestController
@AllArgsConstructor
@RequestMapping("/")
public class AuthController {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/api/auth/join")
    public ApiResult<?> join(@Valid @RequestBody AuthRequest authRequest)
    throws NotFoundException {
        try {

            // 이메일 중복 여부 검사
            String email = authRequest.getEmail();
            AuthUser user = authRepository.findByEmail(email);
            if(user!=null){
                throw new NotFoundException("already exist user");
            }
            // 유저 생성
            String password=authRequest.getPassword();
            String hashedPassword=passwordEncoder.encode(password);
            AuthUser newUser=new AuthUser(email,hashedPassword,"ROLE_USER");
            AuthUser registeredUser=authRepository.save(newUser);

            return success(new JoinResponse(registeredUser.getId()));

        }
        catch(Exception e){
            return error(e,HttpStatus.BAD_REQUEST);
        }
    }

    // user 권한
    @GetMapping("/api/user")
    public ApiResult<String> user(){
        return success("hello user");
    }

    // manager 권한
    @GetMapping("/api/manager")
    public ApiResult<String> manager(){
        return success("hello manager");
    }

    // admin 권한
    @GetMapping("/api/admin")
    public ApiResult<String> admin(){
        return success("hello admin");
    }




}
