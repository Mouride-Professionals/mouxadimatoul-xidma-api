package com.touba.backend.controller;

import com.touba.backend.controller.api.AccueillantApi;
import com.touba.backend.dto.AccueillantDto;
import com.touba.backend.service.AccueillantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccueillantController implements AccueillantApi {

    private final AccueillantService accueillantService;

    @Override
    public AccueillantDto save(AccueillantDto dto) {
        return accueillantService.save(dto);
    }

    @Override
    public AccueillantDto update(AccueillantDto dto) {
        return accueillantService.update(dto);
    }

    @Override
    public AccueillantDto findByUsername(String username) {
        return accueillantService.findByUsername(username);
    }

    @Override
    public AccueillantDto findById(Long id) {
        return accueillantService.findById(id);
    }

    @Override
    public Page<AccueillantDto> findAllByParams(int page, int size, Long idRes, String search) {
        return accueillantService.findAllByParams(page, size, idRes, search);
    }

    @Override
    public void delete(Long id) {
        accueillantService.delete(id);
    }
}
