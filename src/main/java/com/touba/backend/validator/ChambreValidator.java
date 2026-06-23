package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.ChambreDto;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ChambreValidator {

    public static List<ValidationErrorDto> validate(ChambreDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (dto.getPavillon() == null) {
            errors.add(ValidationErrorDto.of("pavillon", ErrorCode.CHAMBRE_PAVILLON_REQUIRED));
        }
        if (dto.getNombrePlace() == null) {
            errors.add(ValidationErrorDto.of("nombrePlace", ErrorCode.CHAMBRE_CAPACITY_REQUIRED));
        }else if (dto.getNombrePlace() < 0) {
            errors.add(ValidationErrorDto.of("nombrePlace", ErrorCode.CHAMBRE_CAPACITY_POSITIVE));
        }
        if (!StringUtils.hasLength(dto.getNumero())) {
            errors.add(ValidationErrorDto.of("numero", ErrorCode.CHAMBRE_NUMBER_REQUIRED));
        }
        return errors;
    }

    public static List<ValidationErrorDto> validateUpdate(ChambreDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (dto.getId() == null) {
            errors.add(ValidationErrorDto.of("id", ErrorCode.CHAMBRE_ID_REQUIRED));
        }
        errors.addAll(validate(dto));
        return errors;
    }

}
