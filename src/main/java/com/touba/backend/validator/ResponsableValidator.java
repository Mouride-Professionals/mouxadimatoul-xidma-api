package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.ResponsableDto;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResponsableValidator {

    public static List<ValidationErrorDto> validate(ResponsableDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add(ValidationErrorDto.of("nom", ErrorCode.RESPONSABLE_LAST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add(ValidationErrorDto.of("prenom", ErrorCode.RESPONSABLE_FIRST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add(ValidationErrorDto.of("telephone", ErrorCode.RESPONSABLE_PHONE_REQUIRED));
        }
        if (dto.getResidence() == null) {
            errors.add(ValidationErrorDto.of("residence", ErrorCode.RESPONSABLE_RESIDENCE_REQUIRED));
        }
        return errors;
    }


}
