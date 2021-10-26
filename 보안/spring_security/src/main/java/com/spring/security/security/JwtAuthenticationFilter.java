package com.spring.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring.security.dto.LoginResponse;
import com.spring.security.model.AuthUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.spring.security.dto.ApiUtils.success;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // 로그인을 처리할 url 을 지정해준다.
        setFilterProcessesUrl("/api/auth/login");
    }

    // 로그인 요청을 하면 로그인 시도를 위해 실행되는 함수이다.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{

            System.out.println("aa");

            // request 를 읽어와서 authenticationManager 에게 보낼 UsernamePasswordAuthenticationToken 을 생성한다.
            ObjectMapper om=new ObjectMapper();
            AuthUser user=om.readValue(request.getInputStream(),AuthUser.class);

            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

            // AuthUserDetailsService 의 loadByUsername 함수가 실행된다.
            Authentication authentication=
                    authenticationManager.authenticate(authenticationToken);

            return authentication;

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    // attemptAuthentication 이 성공적으로 실행되면 호출되고 이때 JWT 토큰을 만들어서 response 에 담아준다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        AuthUserDetails userDetails=(AuthUserDetails)authResult.getPrincipal();

        // jwt token 을 생성한다.
        String jwtToken=JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
                .withClaim("id",userDetails.getUser().getId())
                .withClaim("email",userDetails.getUsername())
                .sign(Algorithm.HMAC512("jwt sign value"));

        // id와 token 을 response body 에 json string 형태로 넣는다.
        response.setContentType("application/json");
        LoginResponse loginResponse=new LoginResponse(userDetails.getUser().getId(),"Bearer "+jwtToken);
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(success(loginResponse)));
    }
}
