package com.example.authjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDto {

    private String field;

    private String code;

    public static ValidationErrorDto of(String field, String code) {
        return ValidationErrorDto.builder()
                .field(field)
                .code(code)
                .build();
    }
}
