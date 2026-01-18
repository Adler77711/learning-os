package com.personallearning.learning_os.common.response;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;

import com.personallearning.learning_os.dto.ActivityResponse;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;
    private Instant timestamp;

    // public static <T> ApiResponse<List<ActivityResponse>> ok(T data) {
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.data = data;
        r.timestamp = Instant.now();
        return r;
    }

    public static <T> ApiResponse<T> fail(ApiError error) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.error = error;
        r.timestamp = Instant.now();
        return r;
    }

    // getters
    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public ApiError getError() { return error; }
    public Instant getTimestamp() { return timestamp; }
}
