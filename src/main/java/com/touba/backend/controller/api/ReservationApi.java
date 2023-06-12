package com.touba.backend.controller.api;

import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("api/v1/reservations")
public interface ReservationApi {

    @PostMapping()
    List<ReservationDto> save(@RequestBody ReservationRequestBody request);

    @GetMapping("/pavillon/{pavillon}/{debut}/{fin}")
    List<ReservationDto> findByPeriodAndPavillon(
            @PathVariable Date debut,
            @PathVariable Date fin,
            @PathVariable Long pavillon
    );

}
