package com.spring.route;

import com.spring.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaMQReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:kafka-mq")
                .to("log:kafka-receiver");
    }
}
