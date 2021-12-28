package com.spring.batch.helloworld.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HelloworldJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before starting job "+jobExecution.getJobInstance().getJobName());
        System.out.println("before starting job - job execution context : "+jobExecution.getExecutionContext().toString());
        jobExecution.getExecutionContext().put("my name","hyun");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after starting job - job execution context : "+jobExecution.getExecutionContext().toString());
    }
}
