package com.touba.backend.controller;

import com.touba.backend.controller.api.ResponsableApi;
import com.touba.backend.dto.ResponsableDto;
import com.touba.backend.service.ResponsableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResponsableController implements ResponsableApi {

    private final ResponsableService responsableService;

    @Override
    public ResponsableDto save(ResponsableDto dto) {
        return responsableService.save(dto);
    }

    @Override
    public ResponsableDto findById(Long id) {
        return responsableService.findById(id);
    }

    @Override
    public Page<ResponsableDto> findAllBySearch(int page, int size, String search, Long residence) {
        return responsableService.findAllBySearch(page, size, search, residence);
    }

    @Override
    public void delete(Long id) {
        responsableService.delete(id);
    }
}
