package com.spring.batch.helloworld.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("before starting step - job execution context : "+stepExecution.getExecutionContext().toString());
        System.out.println("inside step - job parameter : "+stepExecution.getJobParameters());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("after starting step - job execution context : "+stepExecution.getExecutionContext().toString());
        return null;
    }
}
