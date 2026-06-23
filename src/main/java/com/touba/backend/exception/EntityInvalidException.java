package com.touba.backend.exception;

import com.example.authjwt.dto.ValidationErrorDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityInvalidException extends RuntimeException{

    public static final String DEFAULT_CODE = ErrorCode.VALIDATION_INVALID_ENTITY;

    @Getter
    private final String message;

    @Getter
    private final String code;

    @Getter
    private List<ValidationErrorDto> validationErrors = new ArrayList<>();

    public EntityInvalidException(String message) {
        this(message, DEFAULT_CODE);
    }

    public EntityInvalidException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public EntityInvalidException(String message, List<ValidationErrorDto> validationErrors) {
        this(message, DEFAULT_CODE, validationErrors);
    }

    public EntityInvalidException(String message, String code, List<ValidationErrorDto> validationErrors) {
        super(message);
        this.message = message;
        this.code = code;
        this.validationErrors = validationErrors == null ? Collections.emptyList() : validationErrors;
    }

}
