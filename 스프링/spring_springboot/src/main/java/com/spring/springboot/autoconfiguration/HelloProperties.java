package com.spring.springboot.autoconfiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
// application.properties 에서 prefix+변수로 값을 읽어온다.
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String userName;
}
