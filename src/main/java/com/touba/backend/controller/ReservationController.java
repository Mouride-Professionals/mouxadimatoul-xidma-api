package com.touba.backend.controller;

import com.touba.backend.controller.api.ReservationApi;
import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import com.touba.backend.service.ReservationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public ReservationDto update(ReservationDto dto) {
        return reservationService.update(dto);
    }

    @Override
    public ReservationDto findById(Long id) {
        return reservationService.findById(id);
    }

    @Override
    public List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon) {
        return reservationService.findByPeriodAndPavillon(debut, fin, pavillon);
    }

    @Override
    public Page<ReservationDto> findAll(int page, int size, int year, Long event, Long residence, int presence) {
        return reservationService.findAll(page, size, year, event, residence, presence);
    }

    @Override
    public void exportFile(HttpServletResponse response, Long residence, int year, Long event, int presence, String locale) throws IOException {
        reservationService.exportExcelFile(response, residence, year, event, presence, locale);
    }

    @Override
    public void exportPdfFile(HttpServletResponse response, Long residence, int year, Long event, int presence, String locale) throws IOException {
        reservationService.exportPdfFile(response, residence, year, event, presence, locale);
    }

    @Override
    public void delete(Long id) {
        reservationService.delete(id);
    }
}
