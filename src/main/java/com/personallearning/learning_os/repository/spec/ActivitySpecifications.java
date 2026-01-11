package com.personallearning.learning_os.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import com.personallearning.learning_os.domain.*;

import java.time.LocalDate;

public class ActivitySpecifications {

    public static Specification<Activity> hasType(ActivityType type) {
        return (root, query, cb) -> type == null ? cb.conjunction()
                : cb.equal(root.get("type"), type);
    }

    public static Specification<Activity> dateFrom(LocalDate from) {
        return (root, query, cb) -> from == null ? cb.conjunction()
                : cb.greaterThanOrEqualTo(root.get("activityDate"), from);
    }

    public static Specification<Activity> dateTo(LocalDate to) {
        return (root, query, cb) -> to == null ? cb.conjunction()
                : cb.lessThanOrEqualTo(root.get("activityDate"), to);
    }

    public static Specification<Activity> qLike(String q) {
        return (root, query, cb) -> {
            if (q == null || q.isBlank()) return cb.conjunction();
            String like = "%" + q.trim().toLowerCase() + "%";
            // title 允许为空时，coalesce 防止 null 影响 like
            return cb.or(
                    cb.like(cb.lower(cb.coalesce(root.get("title"), "")), like),
                    cb.like(cb.lower(root.get("content")), like)
            );
        };
    }
}
