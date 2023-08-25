package com.touba.backend.service;

import com.touba.backend.dto.ResponsableDto;
import org.springframework.data.domain.Page;

public interface ResponsableService {
    ResponsableDto save(ResponsableDto dto);

    ResponsableDto findById(Long id);

    Page<ResponsableDto> findAllBySearch(int page, int size, String search, Long residence);

    void delete(Long id);
}
