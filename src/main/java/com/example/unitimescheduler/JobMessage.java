package com.example.unitimescheduler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobMessage {
    @JsonProperty("job_id")
    private String jobId;
    private List<String> inputFilePaths;
    @JsonProperty("export_dir")
    private String outputFolder;

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public List<String> getInputFilePaths() { return inputFilePaths; }
    public void setInputFilePaths(List<String> inputFilePaths) { this.inputFilePaths = inputFilePaths; }

    public String getOutputFolder() { return outputFolder; }
    public void setOutputFolder(String outputFolder) { this.outputFolder = outputFolder; }
} 