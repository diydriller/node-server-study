package com.spring.springboot.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

    @Pointcut("@annotation(com.spring.springboot.aop.config.BeforePrint)")
    public void beforePrint(){};
    @Pointcut("@annotation(com.spring.springboot.aop.config.AfterPrint)")
    public void afterPrint(){};
    @Pointcut("@annotation(com.spring.springboot.aop.config.AroundPrint)")
    public void aroundPrint(){};

    @Before("beforePrint()")
    public void beforePrint(JoinPoint jp){
        System.out.println("before");
    }

    @After("afterPrint()")
    public void afterPrint(JoinPoint jp){
        System.out.println("after");
    }

    @Around("aroundPrint()")
    public String aroundPrint(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("before");
        try{
            return (String)jp.proceed();
        }
        finally {
            System.out.println("after");
        }
    }


}
