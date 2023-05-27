package com.touba.backend.validator;

import com.touba.backend.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto.getRole() == null) {
            errors.add("Le role est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add("Le prénom est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Le nom est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add("Le téléphone est obligatoire");
        }
        return errors;
    }

}
