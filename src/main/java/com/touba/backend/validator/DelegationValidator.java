package com.touba.backend.validator;

import com.touba.backend.dto.DelegationDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DelegationValidator {

    public static List<String> validate(DelegationDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Le nom de la délégation est obligatoire");
        }
        if (dto.getNombre() == null) {
            errors.add("Le nombre de personnes est obligatoire");
        }
        if (dto.getChef() == null) {
            errors.add("Ajouter au moins le chef de délégation");
        }
        return errors;
    }

}
