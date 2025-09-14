package com.example.unitimescheduler.service;

import com.example.unitimescheduler.feign.FileServiceClient;
import com.example.unitimescheduler.models.JobMessage;
import com.example.unitimescheduler.models.StatusMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchedulerServiceTest {

    @InjectMocks
    private SchedulerService schedulerService;
    private FileServiceClient fileServiceClient;

    @BeforeEach
    void setUp() {
        fileServiceClient = Mockito.mock(FileServiceClient.class);
        schedulerService = new SchedulerService(fileServiceClient);
    }

    @Test
    void testProcessJobWithEmptyPaths() {
        JobMessage job = new JobMessage();
        job.setJobId("job-1");
        job.setGeneratedTimetablePaths(Collections.emptyList());

        StatusMessage status = schedulerService.processJob(job);

        assertEquals("STARTED", status.getStatus());
        assertEquals("Job started", status.getMessage());
    }

    @Test
    void testProcessJobSuccess() throws Exception {
        JobMessage job = new JobMessage();
        job.setJobId("job-2");
        job.setGeneratedTimetablePaths(List.of("path1"));

        MultipartFile mockFile = new MockMultipartFile(
                "file", "solutions_instructors.json",
                "application/json", "{}".getBytes()
        );

        when(fileServiceClient.uploadFile(ArgumentMatchers.any())).thenReturn("uploaded.json");

        StatusMessage status = schedulerService.processJob(job);

        assertEquals("COMPLETED", status.getStatus());
        assertEquals("Job finished successfully", status.getMessage());
        assertEquals("uploaded.json", status.getResultFile());
    }

    @Test
    void testProcessJobFailure() throws Exception {
        JobMessage job = new JobMessage();
        job.setJobId("job-3");
        job.setGeneratedTimetablePaths(List.of("path1"));

        when(fileServiceClient.uploadFile(ArgumentMatchers.any())).thenThrow(new RuntimeException("upload failed"));

        StatusMessage status = schedulerService.processJob(job);

        assertEquals("FAILED", status.getStatus());
        assertTrue(status.getMessage().contains("upload failed"));
    }
}