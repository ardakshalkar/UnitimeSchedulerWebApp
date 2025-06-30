package com.example.unitimescheduler;

public class StatusMessage {
    private String jobId;
    private String status;
    private String resultFile;
    private String message;

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getResultFile() { return resultFile; }
    public void setResultFile(String resultFile) { this.resultFile = resultFile; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
} 