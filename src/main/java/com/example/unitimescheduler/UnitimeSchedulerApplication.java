package com.example.unitimescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnitimeSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnitimeSchedulerApplication.class, args);
    }
    // mvn spring-boot:run
// RUN DOCKER CONTAINER
    //docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
} 