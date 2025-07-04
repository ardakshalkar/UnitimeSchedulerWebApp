package com.example.unitimescheduler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobMessage {
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("generated_timetable_paths")
    private List<String> generatedTimetablePaths;
    @JsonProperty("timetableConfigurations")
    @JsonAlias({"export_dir"})
    private String timetableConfigurations;

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public List<String> getGeneratedTimetablePaths() { return generatedTimetablePaths; }
    public void setGeneratedTimetablePaths(List<String> generatedTimetablePaths) { this.generatedTimetablePaths = generatedTimetablePaths; }

    public String getTimetableConfigurations() { return timetableConfigurations; }
    public void setTimetableConfigurations(String timetableConfigurations) { this.timetableConfigurations = timetableConfigurations; }
} 