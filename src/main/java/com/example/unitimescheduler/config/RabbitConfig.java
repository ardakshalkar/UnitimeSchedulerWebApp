package com.example.unitimescheduler.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue jobQueue(){
        return  new Queue("jobQueue", true);
    }

    @Bean
    public Queue jobStatusQueue() {
        return new Queue("jobStatusQueue", true);
    }
} 