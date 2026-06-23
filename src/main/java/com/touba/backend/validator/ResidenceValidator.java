package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResidenceValidator {

    public static List<ValidationErrorDto> validate(ResidenceRequest req) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(req.getLibelle())) {
            errors.add(ValidationErrorDto.of("libelle", ErrorCode.RESIDENCE_LABEL_REQUIRED));
        }
        if (!StringUtils.hasLength(req.getAdresse())) {
            errors.add(ValidationErrorDto.of("adresse", ErrorCode.RESIDENCE_ADDRESS_REQUIRED));
        }
        if (!StringUtils.hasLength(req.getTelephoneResidence())) {
            errors.add(ValidationErrorDto.of("telephoneResidence", ErrorCode.RESIDENCE_PHONE_REQUIRED));
        }
        if (!StringUtils.hasLength(req.getNom())) {
            errors.add(ValidationErrorDto.of("nom", ErrorCode.RESIDENCE_MANAGER_LAST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(req.getPrenom())) {
            errors.add(ValidationErrorDto.of("prenom", ErrorCode.RESIDENCE_MANAGER_FIRST_NAME_REQUIRED));
        }
        if (!StringUtils.hasLength(req.getTelephone())) {
            errors.add(ValidationErrorDto.of("telephone", ErrorCode.RESIDENCE_MANAGER_PHONE_REQUIRED));
        }
        return errors;
    }

    public static List<ValidationErrorDto> validate(ResidenceDto dto) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getLibelle())) {
            errors.add(ValidationErrorDto.of("libelle", ErrorCode.RESIDENCE_LABEL_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getAdresse())) {
            errors.add(ValidationErrorDto.of("adresse", ErrorCode.RESIDENCE_ADDRESS_REQUIRED));
        }
        if (!StringUtils.hasLength(dto.getTelephoneResidence())) {
            errors.add(ValidationErrorDto.of("telephoneResidence", ErrorCode.RESIDENCE_PHONE_REQUIRED));
        }
        if (dto.getResponsable() == null) {
            errors.add(ValidationErrorDto.of("responsable", ErrorCode.RESIDENCE_MANAGER_REQUIRED));
        }
        return errors;
    }

}
