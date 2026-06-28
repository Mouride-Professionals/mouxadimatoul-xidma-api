package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.request.AssignmentRequest;
import com.touba.backend.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class AssignmentValidator {

    public static List<ValidationErrorDto> validate(AssignmentRequest request) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (request == null) {
            errors.add(ValidationErrorDto.of("request", ErrorCode.VALIDATION_ASSIGNMENT_INVALID));
            return errors;
        }
        if (request.getAgentId() == null) {
            errors.add(ValidationErrorDto.of("agentId", ErrorCode.ASSIGNMENT_AGENT_REQUIRED));
        }
        if (request.getResidenceId() == null) {
            errors.add(ValidationErrorDto.of("residenceId", ErrorCode.ASSIGNMENT_RESIDENCE_REQUIRED));
        }
        if (request.getResponsibilities() == null || request.getResponsibilities().isEmpty()) {
            errors.add(ValidationErrorDto.of("responsibilities", ErrorCode.ASSIGNMENT_RESPONSIBILITIES_REQUIRED));
        }
        return errors;
    }

    private AssignmentValidator() {
    }
}
