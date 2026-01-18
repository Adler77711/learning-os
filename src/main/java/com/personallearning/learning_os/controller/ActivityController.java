package com.personallearning.learning_os.controller;

import com.personallearning.learning_os.domain.ActivityType;
import com.personallearning.learning_os.dto.ActivityCreateRequest;
import com.personallearning.learning_os.dto.ActivityResponse;
import com.personallearning.learning_os.service.ActivityService;
import com.personallearning.learning_os.common.response.*;

import jakarta.validation.Valid;

import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService activityService) {
        this.service = activityService;
    }
    
    @PostMapping
    public ActivityResponse create(@Valid @RequestBody ActivityCreateRequest request) {
        return service.create(request);
    }

    @GetMapping("/api/activities")
    public List<ActivityResponse> list() {
        return service.list();
    }
    @GetMapping(value = "/api/activities", params = "q")
    public ApiResponse<Page<ActivityResponse>> search(
            @RequestParam(required = false) ActivityType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(service.search(type, from, to, q, page, size));
        // return ApiResponse.ok(service.search(type, from, to, q, page, size).getContent());
    }
}
