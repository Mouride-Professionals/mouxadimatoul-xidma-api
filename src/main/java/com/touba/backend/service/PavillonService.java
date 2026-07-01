package com.touba.backend.service;

import com.touba.backend.dto.PavillonDto;

import java.util.List;

public interface PavillonService {

    PavillonDto save(PavillonDto dto);

    PavillonDto update(Long id, PavillonDto dto);

    List<PavillonDto> findAll();

    List<PavillonDto> findAllByResidence(Long id);

    PavillonDto findById(Long id);

}
