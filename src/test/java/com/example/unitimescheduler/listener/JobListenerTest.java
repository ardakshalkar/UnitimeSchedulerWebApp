package com.example.unitimescheduler.listener;

import com.example.unitimescheduler.StatusPublisher;
import com.example.unitimescheduler.models.JobMessage;
import com.example.unitimescheduler.models.StatusMessage;
import com.example.unitimescheduler.service.SchedulerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobListenerTest {
    @Mock
    private SchedulerService schedulerService;
   @Mock
    private StatusPublisher statusPublisher;

   @InjectMocks
   private JobListener jobListener;

    @Test
    void testReceiveJobSuccess() {
        JobMessage jobMessage = new JobMessage();
        jobMessage.setJobId("123");

        StatusMessage mockStatus = new StatusMessage();
        mockStatus.setJobId("123");
        mockStatus.setStatus("COMPLETED");

        when(schedulerService.processJob(jobMessage)).thenReturn(mockStatus);

        jobListener.receiveJob(jobMessage);

        verify(schedulerService, times(1)).processJob(jobMessage);
        verify(statusPublisher, times(1)).sendStatus(mockStatus);
    }

    @Test
    void testReceiveJobWithExceptionInProcessJob() {
        JobMessage jobMessage = new JobMessage();
        jobMessage.setJobId("456");

        when(schedulerService.processJob(any()))
                .thenThrow(new RuntimeException("boom"));

        assertThrows(RuntimeException.class, () -> jobListener.receiveJob(jobMessage));

        verify(schedulerService, times(1)).processJob(jobMessage);
        verify(statusPublisher, never()).sendStatus(any());
    }

}