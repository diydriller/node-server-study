package com.spring.redis.config.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.redis.dto.CreateUserRequestDto;
import com.spring.redis.model.User;
import com.spring.redis.repository.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class RedisSessionAop {

    @Autowired
    private UserRepository userRepository;

    @Pointcut("@annotation(com.spring.redis.config.aop.RedisSessionCheck)")
    public void redisSessionCheck(){};

    @Pointcut("@annotation(com.spring.redis.config.aop.RedisSessionSet)")
    public void redisSessionSet(){};

    @Before("redisSessionCheck()")
    public void redisSessionCheck(JoinPoint jp) throws Exception {
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes()))
                .getRequest().getSession();
        String sessionId=null;

        Object[] parameterValues = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        for (int i = 0; i < method.getParameters().length; i++) {
            String parameterName = method.getParameters()[i].getName();
            if(parameterName.equals("sessionId")){
                sessionId=(String)parameterValues[i];
            }
        }

        if(session.getAttribute("login_"+sessionId)==null){
            throw new Exception("not exist user");
        }
    }

    @Before("redisSessionSet()")
    public void redisSessionSet(JoinPoint jp) throws Exception {
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes()))
                .getRequest().getSession();

        User user=new User();
        Object[] parameterValues = jp.getArgs();
        for(Object parameterValue:parameterValues){
            if(parameterValue instanceof CreateUserRequestDto){
                CreateUserRequestDto requestDto=(CreateUserRequestDto) parameterValue;
                user.setEmail(requestDto.getEmail());
                user.setName(requestDto.getName());
            }
        }

        User savedUser=userRepository.save(user);
        ObjectMapper objectMapper=new ObjectMapper();
        String userJson=objectMapper.writeValueAsString(savedUser);
        session.setAttribute("login_"+savedUser.getId(),userJson);

    }
}
