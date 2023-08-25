package com.touba.backend.validator;

import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResidenceValidator {

    public static List<String> validate(ResidenceRequest req) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(req.getLibelle())) {
            errors.add("Le libellé doit être obligatoire");
        }
        if (!StringUtils.hasLength(req.getAdresse())) {
            errors.add("Le'adresse doit être obligatoire");
        }
        if (!StringUtils.hasLength(req.getTelephoneResidence())) {
            errors.add("Le téléphone doit être obligatoire");
        }
        if (!StringUtils.hasLength(req.getNom())) {
            errors.add("Le nom du responsable doit être obligatoire");
        }
        if (!StringUtils.hasLength(req.getPrenom())) {
            errors.add("Le prénom du responsable doit être obligatoire");
        }
        if (!StringUtils.hasLength(req.getTelephone())) {
            errors.add("Le téléphone responsable doit être obligatoire");
        }
        return errors;
    }

    public static List<String> validate(ResidenceDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getLibelle())) {
            errors.add("Le libellé doit être obligatoire");
        }
        if (!StringUtils.hasLength(dto.getAdresse())) {
            errors.add("Le'adresse doit être obligatoire");
        }
        if (!StringUtils.hasLength(dto.getTelephoneResidence())) {
            errors.add("Le téléphone doit être obligatoire");
        }
        if (dto.getResponsable() == null) {
            errors.add("Le responsable doit être obligatoire");
        }
        return errors;
    }

}
