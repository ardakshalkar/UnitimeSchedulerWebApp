package com.example.unitimescheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    private final ConcurrentMap<String, StatusMessage> jobStatusMap = new ConcurrentHashMap<>();

    public StatusMessage processJob(JobMessage jobMessage) {
        StatusMessage status = new StatusMessage();
        status.setJobId(jobMessage.getJobId());
        status.setStatus("STARTED");
        status.setMessage("Job started");
        jobStatusMap.put(jobMessage.getJobId(), status);
        logger.info("Job {} started", jobMessage.getJobId());
        String resultFile = Paths.get(jobMessage.getOutputFolder(), "job-" + jobMessage.getJobId() + "-result.json").toString();
        try {
            // Simulate scheduler logic and write a JSON result
            Map<String, Object> result = new HashMap<>();
            result.put("jobId", jobMessage.getJobId());
            result.put("status", "COMPLETED");
            result.put("message", "Job processed successfully");
            // Simulate work
            status.setStatus("IN_PROGRESS");
            status.setMessage("Job in progress");
            jobStatusMap.put(jobMessage.getJobId(), status);
            logger.info("Job {} in progress", jobMessage.getJobId());
            Thread.sleep(2000); // Simulate work
            FileWriter writer = new FileWriter(resultFile);
            writer.write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(result));
            writer.close();
            status.setStatus("COMPLETED");
            status.setResultFile(resultFile);
            status.setMessage("Job finished successfully");
            jobStatusMap.put(jobMessage.getJobId(), status);
            logger.info("Job {} completed", jobMessage.getJobId());
        } catch (IOException e) {
            status.setStatus("FAILED");
            jobStatusMap.put(jobMessage.getJobId(), status);
            logger.error("Job {} failed: {}", jobMessage.getJobId(), e.getMessage());
        } catch(Exception e) {
            status.setStatus("FAILED");
            status.setMessage("Error: " + e.getMessage());
            jobStatusMap.put(jobMessage.getJobId(), status);
            logger.error("Job {} failed: {}", jobMessage.getJobId(), e.getMessage());
        }
        return status;
    }

    public StatusMessage getJobStatus(String jobId) {
        return jobStatusMap.get(jobId);
    }
} 