package com.touba.backend.validator;

import com.touba.backend.dto.request.ReservationRequestBody;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReservationValidator {

    public static List<String> validateBody(ReservationRequestBody request) {
        List<String> errors = new ArrayList<>();
        if (request.getPeriod() == null) {
            errors.add("La période est obligatoire");
        } else if (request.getPeriod().getSortie() == null) {
            errors.add("La date de sortie est obligatoire");
        } else if (request.getPeriod().getEntree() == null) {
            errors.add("La date d'entrée est obligatoire");
        }
        if (request.getEvenement() == null) {
            errors.add("L'événement est obbligatoire");
        }
        if (request.getInvites().isEmpty()) {
            errors.add("Veuillez ajouter au moins un invité");
        } else {
            request.getInvites().forEach(inv -> {
                if (!StringUtils.hasLength(inv.getPrenom())) {
                    errors.add("Le prénom de l'invité est obligatoire");
                }
                if (!StringUtils.hasLength(inv.getNom())) {
                    errors.add("Le nom de l'invité est obligatoire");
                }
                if (!StringUtils.hasLength(inv.getTelephone())) {
                    errors.add("Le téléphone de l'invité est obligatoire");
                }
                if (inv.getChambre() == null) {
                    errors.add("La chambre de l'invité est obligatoire");
                }
            });
        }
        return errors;
    }

}
