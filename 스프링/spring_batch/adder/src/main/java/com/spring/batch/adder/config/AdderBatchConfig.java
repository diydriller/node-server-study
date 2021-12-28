package com.spring.batch.adder.config;

import com.spring.batch.adder.processor.InMemoryItemProcessor;
import com.spring.batch.adder.reader.InMemoryReader;

import com.spring.batch.adder.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AdderBatchConfig {

    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    private InMemoryItemProcessor processor;
    @Autowired
    private ConsoleItemWriter writer;


    @Bean
    public InMemoryReader reader(){
        return new InMemoryReader();
    }

    @Bean
    public Step step2(){
        return steps.get("step2")
                .<Integer,Integer>chunk(3)
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job adderJob(){
        return jobs.get("adderJob")
                .start(step2())
                .build();
    }



}
