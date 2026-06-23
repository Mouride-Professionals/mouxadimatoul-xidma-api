package com.touba.backend.validator;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import com.touba.backend.exception.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReservationValidator {

    public static List<ValidationErrorDto> validateBody(ReservationRequestBody request) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (request.getPeriod() == null) {
            errors.add(ValidationErrorDto.of("period", ErrorCode.RESERVATION_PERIOD_REQUIRED));
        } else if (request.getPeriod().getSortie() == null) {
            errors.add(ValidationErrorDto.of("period.sortie", ErrorCode.RESERVATION_DEPARTURE_DATE_REQUIRED));
        } else if (request.getPeriod().getEntree() == null) {
            errors.add(ValidationErrorDto.of("period.entree", ErrorCode.RESERVATION_ARRIVAL_DATE_REQUIRED));
        }
        if (request.getEvenement() == null) {
            errors.add(ValidationErrorDto.of("evenement", ErrorCode.RESERVATION_EVENT_REQUIRED));
        }
        if (request.getInvites().isEmpty()) {
            errors.add(ValidationErrorDto.of("invites", ErrorCode.RESERVATION_GUESTS_REQUIRED));
        } else {
            request.getInvites().forEach(inv -> {
                if (!StringUtils.hasLength(inv.getPrenom())) {
                    errors.add(ValidationErrorDto.of("invites.prenom", ErrorCode.RESERVATION_GUEST_FIRST_NAME_REQUIRED));
                }
                if (!StringUtils.hasLength(inv.getNom())) {
                    errors.add(ValidationErrorDto.of("invites.nom", ErrorCode.RESERVATION_GUEST_LAST_NAME_REQUIRED));
                }
                if (!StringUtils.hasLength(inv.getTelephone())) {
                    errors.add(ValidationErrorDto.of("invites.telephone", ErrorCode.RESERVATION_GUEST_PHONE_REQUIRED));
                }
                if (inv.getChambre() == null) {
                    errors.add(ValidationErrorDto.of("invites.chambre", ErrorCode.RESERVATION_GUEST_ROOM_REQUIRED));
                }
            });
        }
        return errors;
    }

}
