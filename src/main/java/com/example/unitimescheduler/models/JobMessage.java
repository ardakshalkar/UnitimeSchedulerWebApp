package com.example.unitimescheduler.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobMessage {
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("generated_timetable_paths")
    private List<String> generatedTimetablePaths;
    @JsonProperty("timetableConfigurations")
    @JsonAlias({"export_dir"})
    private String timetableConfigurations;

}