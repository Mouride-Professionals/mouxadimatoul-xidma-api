package com.touba.backend.validator;

import com.touba.backend.dto.InviteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class InviteValidator {

    public static List<String> validate(InviteDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add("le prénom est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("le nom est obligatoire");
        }
        if (!StringUtils.hasLength(dto.getTelephone())) {
            errors.add("le téléphone est obligatoire");
        }
        if (dto.getDelegation() == null) {
            errors.add("la délégation est obligatoire");
        }
        return errors;
    }

}
