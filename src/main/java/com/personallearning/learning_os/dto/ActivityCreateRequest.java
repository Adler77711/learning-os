package com.personallearning.learning_os.dto;

import com.personallearning.learning_os.domain.ActivityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ActivityCreateRequest {

    @NotNull
    private ActivityType type;

    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDate activityDate;

    // getters & setters
    public ActivityType getType() { return type; }
    public void setType(ActivityType type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDate getActivityDate() { return activityDate; }
    public void setActivityDate(LocalDate activityDate) { this.activityDate = activityDate; }
}
