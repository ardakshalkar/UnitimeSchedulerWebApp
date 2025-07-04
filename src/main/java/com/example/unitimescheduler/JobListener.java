package com.example.unitimescheduler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobListener {
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private StatusPublisher statusPublisher;

    @RabbitListener(queues = "${job.queue}")
    public void receiveJob(JobMessage jobMessage) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.util.Map<String, Object> map = mapper.convertValue(jobMessage, java.util.Map.class);
            System.out.println("Received JobMessage fields:");
            for (java.util.Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StatusMessage status = schedulerService.processJob(jobMessage);
        statusPublisher.sendStatus(status);
    }
} 