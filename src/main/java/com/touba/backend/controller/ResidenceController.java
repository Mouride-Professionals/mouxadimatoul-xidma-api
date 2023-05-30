package com.touba.backend.controller;

import com.touba.backend.controller.api.ResidenceApi;
import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;
import com.touba.backend.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResidenceController implements ResidenceApi {

    @Autowired
    private ResidenceService residenceService;

    @Override
    public ResidenceDto save(ResidenceRequest request) {
        return residenceService.save(request);
    }

    @Override
    public ResidenceDto update(ResidenceDto dto) {
        return residenceService.update(dto);
    }

    @Override
    public List<ResidenceDto> findAll() {
        return residenceService.findAll();
    }

    @Override
    public ResidenceDto findById(Long id) {
        return residenceService.findById(id);
    }

    @Override
    public ResidenceDto findByResponsable(String responsable) {
        return residenceService.findByResponsable(responsable);
    }
}
