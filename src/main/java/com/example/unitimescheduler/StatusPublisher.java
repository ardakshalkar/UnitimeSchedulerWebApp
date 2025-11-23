package com.example.unitimescheduler;

import com.example.unitimescheduler.models.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Value("${job.status-queue}")
    private String statusQueue;


    public void sendStatus(StatusMessage statusMessage) {
        rabbitTemplate.convertAndSend(statusQueue, statusMessage);
    }
} 