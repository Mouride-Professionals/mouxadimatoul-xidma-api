package com.touba.backend.controller.api;

import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/residences")
public interface ResidenceApi {

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResidenceDto save(@ModelAttribute ResidenceRequest request);

    ResidenceDto update(ResidenceDto dto);

    @GetMapping
    List<ResidenceDto> findAll();

    @GetMapping("/{id}")
    ResidenceDto findById(@PathVariable Long id);

    @GetMapping("/responsable/{responsable}")
    ResidenceDto findByResponsable(@PathVariable String responsable);

}
