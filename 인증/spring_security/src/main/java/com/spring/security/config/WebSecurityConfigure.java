package com.spring.security.config;

import com.spring.security.repository.AuthRepository;
import com.spring.security.security.JwtAuthenticationFilter;
import com.spring.security.security.JwtAuthorizationFilter;
import com.spring.security.security.StartFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final AuthRepository authRepository;

    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /* security filter 이후로 filter 등록한다. 숫자가 낮을수록 우선순위가 높다.
    @Bean
    public FilterRegistrationBean<CustomFilter> filter1(){
        FilterRegistrationBean<CustomFilter> bean=new FilterRegistrationBean<>(new CustomFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 시작 필터 추가
        http.addFilterBefore(new StartFilter(), SecurityContextPersistenceFilter.class);

        http
                // 임의의 난수값이 들어있는 csrf 토큰을 쿠키에 저장시키고
                // 클라이언트 요청시 form 에 있는 토큰값과 쿠키에 있는 토큰값의 일치여부로 csrf 공격 방지한다. (Csrf Filter)
                //.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable();

        //session 사용안한다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // cors 설정으로 클라이언트에게 이 설정을 보내서 자원공유가 가능한지 알려준다. (Cors Filter)
                .addFilter(corsFilter)

                .formLogin().disable()

                // header 에서 Authorization 키 값으로 id , password 담아서 보내는 인증 방식이다.
                .httpBasic().disable()

                // jwt 생성 필터 - UsernamePasswordAuthenticationFilter
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))

                // jwt 검증 필터 - BasicAuthenticationFilter
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),authRepository))

                // 인증과 권한을 확인한다. (BasicAuthentication Filter)
                .authorizeRequests()
                .antMatchers("/api/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

    }
}
