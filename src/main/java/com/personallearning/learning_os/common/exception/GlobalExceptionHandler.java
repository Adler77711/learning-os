package com.personallearning.learning_os.common.exception;

import com.personallearning.learning_os.common.response.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> handleIllegalArg(IllegalArgumentException ex) {
        return ApiResponse.fail(new ApiError("BAD_REQUEST", ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidation(MethodArgumentNotValidException ex) {
        var violations = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ApiError.FieldViolation(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
        return ApiResponse.fail(new ApiError("VALIDATION_ERROR", "Request validation failed", violations));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleOther(Exception ex) {
        return ApiResponse.fail(new ApiError("INTERNAL_ERROR", "Unexpected error"));
    }
}

