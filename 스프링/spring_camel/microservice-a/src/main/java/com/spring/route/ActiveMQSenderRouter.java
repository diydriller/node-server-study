package com.spring.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("timer:active-mq-timer?period=1000")
//                .transform().constant("message for Active MQ")
//                .log("${body}")
//                .to("activemq:active-mq");

//        from("file:files/json")
//                .log("${body}")
//                .to("activemq:active-mq");

        from("file:files/xml")
                .log("${body}")
                .to("activemq:active-mq-xml");


    }
}
