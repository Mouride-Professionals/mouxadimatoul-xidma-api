package com.touba.backend.service;

import com.touba.backend.dto.DelegationDto;
import org.springframework.data.domain.Page;

public interface DelegationService {

    DelegationDto save(DelegationDto dto);

    DelegationDto findById(Long id);

    Page<DelegationDto> findAll(int page, int size);

    void delete(Long id);

}
