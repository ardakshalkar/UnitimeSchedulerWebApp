package com.example.unitimescheduler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StatusPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String statusQueue;

    public StatusPublisher(RabbitTemplate rabbitTemplate,
                           @Value("${job.status-queue}") String statusQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.statusQueue = statusQueue;
    }

    public void sendStatus(StatusMessage statusMessage) {
        rabbitTemplate.convertAndSend(statusQueue, statusMessage);
    }
} 