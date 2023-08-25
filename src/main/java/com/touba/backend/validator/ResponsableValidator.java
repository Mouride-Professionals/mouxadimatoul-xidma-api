package com.touba.backend.validator;

import com.touba.backend.dto.ResponsableDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResponsableValidator {

    public static List<String> validate(ResponsableDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Le nom est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add("Le prénom est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add("Le téléphone est obligatoire");
        }
        if (dto.getResidence() == null) {
            errors.add("La résidence est obligatoire");
        }
        return errors;
    }


}
