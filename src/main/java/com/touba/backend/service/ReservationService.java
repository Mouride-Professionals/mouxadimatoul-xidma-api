package com.touba.backend.service;

import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<ReservationDto> save(ReservationRequestBody request);

    ReservationDto findById(Long id);

    List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon);

    Page<ReservationDto> findAll(int page, int size, int year, Long event, int presence);

}
