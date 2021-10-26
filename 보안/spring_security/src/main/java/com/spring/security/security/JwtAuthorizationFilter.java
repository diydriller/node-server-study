package com.spring.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.security.model.AuthUser;
import com.spring.security.repository.AuthRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 인증이나 권한이 필요할 경우 이 필터를 거친다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private AuthRepository authRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthRepository authRepository) {
        super(authenticationManager);
        this.authRepository=authRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwtHeader=request.getHeader("Authorization");

        if(jwtHeader==null||!jwtHeader.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }

        // jwt token 에서 서명된 email 값을 통해 검증한다.
        String jwtToken=jwtHeader.replace("Bearer ","");
        String email= JWT.require(Algorithm.HMAC512("jwt sign value")).build()
                .verify(jwtToken).getClaim("email").asString();


        if(email==null){
            chain.doFilter(request,response);
            return;
        }

        AuthUser user=authRepository.findByEmail(email);
        if(user==null){
            chain.doFilter(request,response);
            return;
        }

        // session 에 저장한다.
        // jwt 를 사용함에도 session 을 사용하는 이유는 security 가 권한관리를 할때 필요하기 때문이다.
        AuthUserDetails userDetails=new AuthUserDetails(user);
        Authentication authentication=
                new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request,response);

    }
}
