package com.personallearning.learning_os.dto;

import com.personallearning.learning_os.domain.Activity;
import com.personallearning.learning_os.domain.ActivityType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivityResponse {
    private Long id;
    private ActivityType type;
    private String title;
    private String content;
    private LocalDate activityDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ActivityResponse from(Activity a) {
        ActivityResponse r = new ActivityResponse();
        r.id = a.getId();
        r.type = a.getType();
        r.title = a.getTitle();
        r.content = a.getContent();
        r.activityDate = a.getActivityDate();
        r.createdAt = a.getCreatedAt();
        r.updatedAt = a.getUpdatedAt();
        return r;
    }

    // getters only
    public Long getId() { return id; }
    public ActivityType getType() { return type; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDate getActivityDate() { return activityDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
