package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.PavillonDto;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PavillonValidator {

    public static List<ValidationErrorDto> validate(PavillonDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getLibelle())) {
            errors.add(ValidationErrorDto.of("libelle", ErrorCode.PAVILLON_LABEL_REQUIRED));
        }
        if (dto.getResidence() == null) {
            errors.add(ValidationErrorDto.of("residence", ErrorCode.PAVILLON_RESIDENCE_REQUIRED));
        }
        return errors;
    }

}
