package com.example.unitimescheduler.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusMessage {
    private String jobId;
    private String status;
    private String resultFile;
    private String message;
} 