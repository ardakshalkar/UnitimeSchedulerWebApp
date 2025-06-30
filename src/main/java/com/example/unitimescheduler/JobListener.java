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
        System.out.println("Received job: " + jobMessage.getJobId());
        StatusMessage status = schedulerService.processJob(jobMessage);
        statusPublisher.sendStatus(status);
    }
} 