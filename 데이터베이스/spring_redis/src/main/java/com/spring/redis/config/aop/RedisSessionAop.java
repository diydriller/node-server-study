package com.spring.redis.config.aop;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@Component
public class RedisSessionAop {


    @Pointcut("@annotation(com.spring.redis.config.aop.RedisSessionCheck)")
    public void redisSessionCheck(){};

    // 세션아이디로 유저조회
    @Before("redisSessionCheck()")
    public void redisSessionCheck(JoinPoint jp) throws Exception {
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes()))
                .getRequest().getSession();
        Long sessionId=null;

        Object[] parameterValues = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        for (int i = 0; i < method.getParameters().length; i++) {
            String parameterName = method.getParameters()[i].getName();
            if(parameterName.equals("sessionId")){
                sessionId=(Long)parameterValues[i];
            }
        }

        if(session.getAttribute("login_"+sessionId)==null){
            throw new Exception("not exist user");
        }
    }


}
