package com.touba.backend.controller.api;

import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequestMapping("api/v1/reservations")
public interface ReservationApi {

    @PostMapping()
    List<ReservationDto> save(@RequestBody ReservationRequestBody request);

    @PutMapping
    ReservationDto update(@RequestBody ReservationDto dto);

    @GetMapping("/{id}")
    ReservationDto findById(@PathVariable Long id);

    @GetMapping("/pavillon/{pavillon}/{debut}/{fin}")
    List<ReservationDto> findByPeriodAndPavillon(
            @PathVariable Date debut,
            @PathVariable Date fin,
            @PathVariable Long pavillon
    );

    @GetMapping()
    Page<ReservationDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "-1") int year,
            @RequestParam(defaultValue = "-1") Long event,
            @RequestParam(defaultValue = "-1") Long residence,
            int presence
    );

    @GetMapping("/exportation/residence/{residence}")
    void exportFile(
            HttpServletResponse response,
            @PathVariable Long residence,
            @RequestParam int year,
            @RequestParam Long event,
            @RequestParam(defaultValue = "-1") int presence,
            @RequestParam(defaultValue = "fr") String locale
    ) throws IOException;

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
