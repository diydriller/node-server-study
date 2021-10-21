package com.spring.springboot.aop;


import com.spring.springboot.aop.config.AfterPrint;
import com.spring.springboot.aop.config.AroundPrint;
import com.spring.springboot.aop.config.BeforePrint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping("/print/add/{integer1}/{integer2}")
    @BeforePrint
    @AfterPrint
    public String printAdd(@PathVariable("integer1") Integer integer1,
                      @PathVariable("integer2") Integer integer2){
        System.out.println(integer1+integer2);
        return "success";
    }


    @GetMapping("/print/sub/{integer1}/{integer2}")
    @AroundPrint
    public String printSub(@PathVariable("integer1") Integer integer1,
                      @PathVariable("integer2") Integer integer2){
        System.out.println(integer1-integer2);
        return "success";
    }



}
