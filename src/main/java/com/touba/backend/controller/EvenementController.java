package com.touba.backend.controller;

import com.touba.backend.controller.api.EvenementApi;
import com.touba.backend.dto.EvenementDto;
import com.touba.backend.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EvenementController implements EvenementApi {

    @Autowired
    private EvenementService evenementService;

    @Override
    public EvenementDto save(EvenementDto dto) {
        return evenementService.save(dto);
    }

    @Override
    public List<EvenementDto> findAll() {
        return evenementService.findAll();
    }

    @Override
    public EvenementDto findById(Long id) {
        return evenementService.findById(id);
    }

    @Override
    public void delete(Long id) {
        evenementService.delete(id);
    }
}
