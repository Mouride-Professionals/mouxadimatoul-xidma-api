package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.InviteDto;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class InviteValidator {

    public static List<ValidationErrorDto> validate(InviteDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add(ValidationErrorDto.of("prenom", ErrorCode.INVITE_FIRST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add(ValidationErrorDto.of("nom", ErrorCode.INVITE_LAST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add(ValidationErrorDto.of("telephone", ErrorCode.INVITE_PHONE_REQUIRED));
        }
        if (dto.getDelegation() == null) {
            errors.add(ValidationErrorDto.of("delegation", ErrorCode.INVITE_DELEGATION_REQUIRED));
        }
        return errors;
    }

}
