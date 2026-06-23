package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.UtilisateurDto;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<ValidationErrorDto> validate(UtilisateurDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add(ValidationErrorDto.of("prenom", ErrorCode.USER_FIRST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add(ValidationErrorDto.of("nom", ErrorCode.USER_LAST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add(ValidationErrorDto.of("telephone", ErrorCode.USER_PHONE_REQUIRED));
        }
        return errors;
    }

}
