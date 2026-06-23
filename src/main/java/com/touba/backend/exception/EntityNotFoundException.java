package com.touba.backend.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException{

    public static final String DEFAULT_CODE = ErrorCode.ENTITY_NOT_FOUND;

    @Getter
    private final String message;

    @Getter
    private final String code;

    public EntityNotFoundException(String message) {
        this(message, DEFAULT_CODE);
    }

    public EntityNotFoundException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

}
