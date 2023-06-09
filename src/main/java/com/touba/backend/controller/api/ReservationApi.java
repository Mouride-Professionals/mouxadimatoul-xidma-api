package com.touba.backend.controller.api;

import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/reservations")
public interface ReservationApi {

    @PostMapping()
    List<ReservationDto> save(@RequestBody ReservationRequestBody request);

}
