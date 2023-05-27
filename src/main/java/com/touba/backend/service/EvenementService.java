package com.touba.backend.service;

import com.touba.backend.dto.EvenementDto;

import java.util.List;

public interface EvenementService {

    EvenementDto save(EvenementDto dto);

    List<EvenementDto> findAll();

    EvenementDto findById(Long id);

    void delete(Long id);

}
