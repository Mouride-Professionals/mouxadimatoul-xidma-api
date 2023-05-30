package com.touba.backend.controller;

import com.touba.backend.controller.api.PavillonApi;
import com.touba.backend.dto.PavillonDto;
import com.touba.backend.service.PavillonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PavillonController implements PavillonApi {

    @Autowired
    private PavillonService pavillonService;

    @Override
    public PavillonDto save(PavillonDto dto) {
        return pavillonService.save(dto);
    }

    @Override
    public List<PavillonDto> findAll() {
        return pavillonService.findAll();
    }

    @Override
    public List<PavillonDto> findAllByResidence(Long id) {
        return pavillonService.findAllByResidence(id);
    }

    @Override
    public PavillonDto findById(Long id) {
        return pavillonService.findById(id);
    }
}
