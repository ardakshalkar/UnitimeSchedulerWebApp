package com.example.unitimescheduler.service;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.example.unitimescheduler.models.JobMessage;
import com.example.unitimescheduler.models.StatusMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.student.StudentEnrollmentWithMultipleConfigurations;

@Service
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    private final ConcurrentMap<String, StatusMessage> jobStatusMap = new ConcurrentHashMap<>();

    public StatusMessage processJob(JobMessage jobMessage) {
        System.out.println("Processing job: " + jobMessage.getJobId());
        System.out.println("TimetableConfigurations: " + jobMessage.getTimetableConfigurations());
        System.out.println("Generated timetable paths: " + jobMessage.getGeneratedTimetablePaths());
        StatusMessage status = new StatusMessage();
        status.setJobId(jobMessage.getJobId());
        status.setStatus("STARTED");
        status.setMessage("Job started");
        jobStatusMap.put(jobMessage.getJobId(), status);
        logger.info("Job {} started", jobMessage.getJobId());
        
        List<String> paths = jobMessage.getGeneratedTimetablePaths();
        if (paths == null || paths.isEmpty()) {
            logger.warn("No generated timetable paths provided for job {}", jobMessage.getJobId());
            return status;
        }
        String resultFile = "C:\\Users\\User\\IdeaProjects\\UnitimeSchedulerWebApp\\solutions_instructors.json";
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
            java.util.List<String> fullPaths = jobMessage.getGeneratedTimetablePaths();
            String exportDir = jobMessage.getTimetableConfigurations();
            java.util.List<String> args = new java.util.ArrayList<>();
            args.add(exportDir);
            for (String path : fullPaths) {
                args.add(new java.io.File(path).getName());
            }
            StudentEnrollmentWithMultipleConfigurations.main(args.toArray(new String[0]));


            status.setStatus("COMPLETED");
            status.setResultFile(resultFile);
            status.setMessage("Job finished successfully");
            jobStatusMap.put(jobMessage.getJobId(), status);
            logger.info("Job {} completed", jobMessage.getJobId());
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