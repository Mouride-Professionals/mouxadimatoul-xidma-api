package com.touba.backend.service;

import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;

import java.util.List;

public interface ResidenceService {

    ResidenceDto save(ResidenceRequest request);

    ResidenceDto update(ResidenceDto dto);

    List<ResidenceDto> findAll();

    ResidenceDto findById(Long id);

    ResidenceDto findByResponsable(String responsable);

}
