package com.spring.springboot.autoconfiguration.hello;

import lombok.AllArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
public class Hello {

    private HelloConfig helloConfig;

    public String hello(){
        LocalTime now=LocalTime.now();
        LocalTime t1=LocalTime.MIDNIGHT;
        LocalTime t2=LocalTime.MIDNIGHT.plusHours(6);
        LocalTime t3= LocalTime.NOON;
        LocalTime t4=LocalTime.NOON.plusHours(6);

        String userName=helloConfig.getProperty("user.name");

        if(now.isAfter(t1)&&now.isBefore(t2)){
            return "hello "+userName+" good night "+new String(Character.toChars(0x0001F319));
        }
        else if(now.isAfter(t2)&&now.isBefore(t3)){
            return "hello "+userName+" good moring "+new String(Character.toChars(0x0001F305));
        }
        else if(now.isAfter(t3)&&now.isBefore(t4)){
            return "hello "+userName+" good daytime "+new String(Character.toChars(0x0001F31E));
        }
        else{
            return "hello "+userName+" good evening "+new String(Character.toChars(0x0001F307));
        }

    }

}
