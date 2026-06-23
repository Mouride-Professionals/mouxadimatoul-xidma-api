package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.DelegationDto;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DelegationValidator {

    public static List<ValidationErrorDto> validate(DelegationDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add(ValidationErrorDto.of("nom", ErrorCode.DELEGATION_NAME_REQUIRED));
        }
        if (dto.getNombre() == null) {
            errors.add(ValidationErrorDto.of("nombre", ErrorCode.DELEGATION_SIZE_REQUIRED));
        }
        if (dto.getChef() == null) {
            errors.add(ValidationErrorDto.of("chef", ErrorCode.DELEGATION_LEADER_REQUIRED));
        }
        return errors;
    }

}
