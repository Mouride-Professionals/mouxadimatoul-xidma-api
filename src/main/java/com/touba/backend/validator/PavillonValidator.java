package com.touba.backend.validator;

import com.touba.backend.dto.PavillonDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PavillonValidator {

    public static List<String> validate(PavillonDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getLibelle())) {
            errors.add("Le libellé doit être obligatoire");
        }
        if (dto.getResidence() == null) {
            errors.add("Le pavillon doit être relié à une résidence");
        }
        return errors;
    }

}
