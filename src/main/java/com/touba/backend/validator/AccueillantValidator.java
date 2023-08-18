package com.touba.backend.validator;

import com.touba.backend.dto.AccueillantDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AccueillantValidator {

    public static List<String> validate(AccueillantDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Le nom de l'accueillant est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add("Le téléphone est obligatoire");
        }
        if (dto.getResidence() == null) {
            errors.add("La résidence de l'accueillant est obligatoire");
        }
        return errors;
    }

}
