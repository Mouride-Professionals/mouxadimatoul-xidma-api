package com.touba.backend.service;

import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<ReservationDto> save(ReservationRequestBody request);

    ReservationDto update(ReservationDto dto);

    ReservationDto findById(Long id);

    List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon);

    Page<ReservationDto> findAll(int page, int size, int year, Long event, Long residence, int presence);

    void exportExcelFile(HttpServletResponse response, Long residence, int year, Long event, int presence, String locale) throws IOException;

    void exportPdfFile(HttpServletResponse response, Long residence, int year, Long event, int presence, String locale) throws IOException;

    void delete(Long id);
}
