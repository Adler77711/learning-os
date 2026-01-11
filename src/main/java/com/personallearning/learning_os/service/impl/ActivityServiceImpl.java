package com.personallearning.learning_os.service.impl;

import com.personallearning.learning_os.domain.Activity;
import com.personallearning.learning_os.domain.ActivityType;
import com.personallearning.learning_os.dto.ActivityCreateRequest;
import com.personallearning.learning_os.dto.ActivityResponse;
import com.personallearning.learning_os.repository.ActivityRepository;
import com.personallearning.learning_os.repository.spec.ActivitySpecifications;
import com.personallearning.learning_os.service.ActivityService;

import org.springframework.data.domain.*;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repo;

    public ActivityServiceImpl(ActivityRepository repo) {
        this.repo = repo;
    }

    @Override
    public ActivityResponse create(ActivityCreateRequest request) {
        // DTO -> Entity
        Activity activity = new Activity();
        activity.setType(request.getType());
        activity.setTitle(request.getTitle());
        activity.setContent(request.getContent());
        activity.setActivityDate(request.getActivityDate());

        // save
        Activity saved = repo.save(activity);

        // Entity -> Response DTO
        return ActivityResponse.from(saved);
    }

    @Override
    public List<ActivityResponse> list() {
        // 按 activityDate 倒序（可选但推荐）
        List<Activity> activities = repo.findAll(Sort.by(Sort.Direction.DESC, "activityDate"));

        return activities.stream()
                .map(ActivityResponse::from)
                .toList();
    }
    
    @Override
    public Page<ActivityResponse> search(ActivityType type,
                                         LocalDate from,
                                         LocalDate to,
                                         String q,
                                         int page,
                                         int size) {

        if (page < 0) throw new IllegalArgumentException("page must be >= 0");
        if (size <= 0 || size > 100) throw new IllegalArgumentException("size must be between 1 and 100");

        Pageable pageable = PageRequest.of(page, size, Sort.by("activityDate").descending());

        Specification<Activity> spec = Specification.where(ActivitySpecifications.hasType(type))
                .and(ActivitySpecifications.dateFrom(from))
                .and(ActivitySpecifications.dateTo(to))
                .and(ActivitySpecifications.qLike(q));

        return repo.findAll(spec, pageable)
                .map(ActivityResponse::from);
    }
}
