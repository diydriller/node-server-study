package com.spring.route;


import org.apache.camel.builder.RouteBuilder;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;



//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingProcessingComponent;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                .log("${body}")
                // transformation ( body changed )
                .transform().constant("Time is "+ LocalDateTime.now())
                .log("${body}")
                .bean(getCurrentTimeBean)
                .log("${body}")
                // processing ( body not changed )
                .bean(loggingProcessingComponent)
                .to("log:first-timer");
    }
}

//@Component
class GetCurrentTimeBean{
    public String getCurrentTime(){
        return "Time is "+LocalDateTime.now();
    }
}

//@Component
class SimpleLoggingProcessingComponent{
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message){
        logger.info("logging message: "+message);
    }

}


