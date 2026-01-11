package com.personallearning.learning_os.service;

import com.personallearning.learning_os.domain.ActivityType;
import com.personallearning.learning_os.dto.ActivityCreateRequest;
import com.personallearning.learning_os.dto.ActivityResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;


public interface ActivityService {
    ActivityResponse create(ActivityCreateRequest request);
    List<ActivityResponse> list();
    Page<ActivityResponse> search(ActivityType type,
                                         LocalDate from,
                                         LocalDate to,
                                         String q,
                                         int page,
                                         int size);
}
