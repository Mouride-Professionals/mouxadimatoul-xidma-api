package com.touba.backend.controller;

import com.touba.backend.controller.api.DelegationApi;
import com.touba.backend.dto.DelegationDto;
import com.touba.backend.service.DelegationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DelegationController implements DelegationApi {

    private final DelegationService delegationService;

    @Override
    public DelegationDto save(DelegationDto dto) {
        return delegationService.save(dto);
    }

    @Override
    public DelegationDto update(DelegationDto dto) {
        return delegationService.update(dto);
    }

    @Override
    public DelegationDto findById(Long id) {
        return delegationService.findById(id);
    }

    @Override
    public Page<DelegationDto> findAll(int page, int size) {
        return delegationService.findAll(page, size);
    }

    @Override
    public void delete(Long id) {
        delegationService.delete(id);
    }
}
