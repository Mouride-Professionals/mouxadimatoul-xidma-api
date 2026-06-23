package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.AccueillantDto;
import com.touba.backend.exception.ErrorCode;
import java.util.List;

public class AccueillantValidator {

    public static List<ValidationErrorDto> validate(AccueillantDto dto) {
        List<ValidationErrorDto> errors = UtilisateurValidator.validate(dto.getUtilisateur());
        if (dto.getResidence() == null) {
            errors.add(ValidationErrorDto.of("residence", ErrorCode.ACCUEILLANT_RESIDENCE_REQUIRED));
        }
        return errors;
    }

}
