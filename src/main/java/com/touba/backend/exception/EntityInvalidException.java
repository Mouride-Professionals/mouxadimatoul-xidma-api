package com.touba.backend.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class EntityInvalidException extends RuntimeException{

    @Getter
    private final String message;

    @Getter
    private List<String> errors = new ArrayList<>();

    public EntityInvalidException(String message) {
        super(message);
        this.message = message;
    }

    public EntityInvalidException(String message, List<String> errors) {
        super(message);
        this.message = message;
        this.errors = errors;
    }

}
