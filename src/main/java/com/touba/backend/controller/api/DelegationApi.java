package com.touba.backend.controller.api;

import com.touba.backend.dto.DelegationDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/delegations")
public interface DelegationApi {

    @PostMapping()
    DelegationDto save(@RequestBody DelegationDto dto);

    @GetMapping("/{id}")
    DelegationDto findById(@PathVariable Long id);

    @GetMapping()
    Page<DelegationDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    );

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

}
