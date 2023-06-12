package com.touba.backend.service;

import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<ReservationDto> save(ReservationRequestBody request);

    ReservationDto findById(Long id);

    List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon);

}
