package com.touba.backend.validator;

import com.touba.backend.dto.ChambreDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ChambreValidator {

    public static List<String> validate(ChambreDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto.getPavillon() == null) {
            errors.add("Le pavillon est obligatoire");
        }
        if (dto.getNombrePlace() == null) {
            errors.add("Le nombre de place est obligatoire");
        }else if (dto.getNombrePlace() < 0) {
            errors.add("Le nombre de place doit être positif");
        }
        if (!StringUtils.hasLength(dto.getNumero())) {
            errors.add("Le numéro de la chambre est obligatoire");
        }
        return errors;
    }

    public static List<String> validateUpdate(ChambreDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto.getId() == null) {
            errors.add("L'id est obligatoire");
        }
        errors = validate(dto);
        return errors;
    }

}
