package com.example.authjwt.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorDto {

    private Integer httpCode;

    private String code;

    private String message;

    private List<String> errors;

    private List<ValidationErrorDto> validationErrors;

}
