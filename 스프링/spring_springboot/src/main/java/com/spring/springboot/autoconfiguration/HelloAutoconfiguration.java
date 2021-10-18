package com.spring.springboot.autoconfiguration;

import com.spring.springboot.hello.Hello;
import com.spring.springboot.hello.HelloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Hello.class 가 존재할때 실행한다.
@ConditionalOnClass(Hello.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoconfiguration {

    @Autowired
    private HelloProperties helloProperties;

    // HelloConfig 를 bean 으로 설정한다.
    @Bean
    @ConditionalOnMissingBean
    public HelloConfig helloConfig(){
        String userName= helloProperties.getUserName()==null
                ?System.getProperty("user.name")
                : helloProperties.getUserName();
        HelloConfig helloConfig=new HelloConfig();
        helloConfig.put("user.name",userName);
        return helloConfig;
    }


    // Hello 를 bean 으로 설정한다.
    @Bean
    @ConditionalOnMissingBean
    public Hello hello(HelloConfig helloConfig){
        return new Hello(helloConfig);
    }

}
