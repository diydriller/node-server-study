package com.spring.route;

import com.spring.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {

    @Autowired
    CurrencyExchangeTransformer currencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
//        from("activemq:active-mq")
//                .to("log:active-mq-receiver");

//        from("activemq:active-mq")
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .bean(currencyExchangeTransformer)
//                .to("log:active-mq-receiver");

        from("activemq:active-mq-xml")
                .unmarshal().jacksonxml(CurrencyExchange.class)
                .to("log:active-mq-xml-receiver");
    }
}

