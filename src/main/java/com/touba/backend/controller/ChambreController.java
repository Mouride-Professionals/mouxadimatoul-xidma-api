package com.touba.backend.controller;

import com.touba.backend.controller.api.ChambreApi;
import com.touba.backend.dto.ChambreDto;
import com.touba.backend.service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChambreController implements ChambreApi {

    @Autowired
    private ChambreService chambreService;

    @Override
    public ChambreDto save(ChambreDto dto) {
        return chambreService.save(dto);
    }

    @Override
    public ChambreDto update(ChambreDto dto) {
        return chambreService.update(dto);
    }

    @Override
    public ChambreDto findById(Long id) {
        return chambreService.findById(id);
    }

    @Override
    public Page<ChambreDto> findAllByPavillon(Long pavillon, int page, int size) {
        return chambreService.findAllByPavillon(pavillon, page, size);
    }
}
