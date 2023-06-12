package com.touba.backend.controller;

import com.touba.backend.controller.api.ReservationApi;
import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import com.touba.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ReservationController implements ReservationApi {

    @Autowired
    private ReservationService reservationService;

    @Override
    public List<ReservationDto> save(ReservationRequestBody request) {
        return reservationService.save(request);
    }

    @Override
    public List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon) {
        return reservationService.findByPeriodAndPavillon(debut, fin, pavillon);
    }
}
