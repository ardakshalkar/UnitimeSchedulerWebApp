package com.example.unitimescheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    public StatusMessage processJob(JobMessage jobMessage) {
        StatusMessage status = new StatusMessage();
        status.setJobId(jobMessage.getJobId());
        String resultFile = Paths.get(jobMessage.getOutputFolder(), "job-" + jobMessage.getJobId() + "-result.json").toString();
        try {
            // Simulate scheduler logic and write a JSON result
            Map<String, Object> result = new HashMap<>();
            result.put("jobId", jobMessage.getJobId());
            result.put("status", "COMPLETED");
            result.put("message", "Job processed successfully");
            FileWriter writer = new FileWriter(resultFile);
            writer.write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(result));
            writer.close();
            status.setStatus("COMPLETED");
            status.setResultFile(resultFile);
            status.setMessage("Job finished successfully");
        } catch (IOException e) {
            status.setStatus("FAILED");
            status.setMessage("Error: " + e.getMessage());
        } catch(Exception e) {
            status.setStatus("FAILED");
            status.setMessage("Error: " + e.getMessage());
        }
        return status;
    }
} 