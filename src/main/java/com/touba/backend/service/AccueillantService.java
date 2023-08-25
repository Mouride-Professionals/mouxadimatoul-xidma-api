package com.touba.backend.service;

import com.touba.backend.dto.AccueillantDto;
import org.springframework.data.domain.Page;

public interface AccueillantService {

    AccueillantDto save(AccueillantDto dto);

    AccueillantDto update(AccueillantDto dto);

    AccueillantDto findById(Long id);

    Page<AccueillantDto> findAllByParams(int page, int size, Long idRes, String search);

    AccueillantDto findByUsername(String username);

    void delete(Long id);

}
