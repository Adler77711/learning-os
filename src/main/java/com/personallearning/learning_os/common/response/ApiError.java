package com.personallearning.learning_os.common.response;

import java.util.List;

public class ApiError {
    private String code;     // e.g. VALIDATION_ERROR
    private String message;  // human readable
    private List<FieldViolation> violations;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError(String code, String message, List<FieldViolation> violations) {
        this.code = code;
        this.message = message;
        this.violations = violations;
    }

    public static class FieldViolation {
        private String field;
        private String reason;

        public FieldViolation(String field, String reason) {
            this.field = field;
            this.reason = reason;
        }
        public String getField() { return field; }
        public String getReason() { return reason; }
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public List<FieldViolation> getViolations() { return violations; }
}
