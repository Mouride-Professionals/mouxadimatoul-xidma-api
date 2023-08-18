package com.touba.backend.controller.api;


import com.touba.backend.dto.AccueillantDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/accueillants")
public interface AccueillantApi {

    @PostMapping
    AccueillantDto save(@RequestBody AccueillantDto dto);

    @PutMapping
    AccueillantDto update(@RequestBody AccueillantDto dto);

    @GetMapping("/{id}")
    AccueillantDto findById(@PathVariable Long id);

    @GetMapping
    Page<AccueillantDto> findAllByParams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "-1", name = "residence") Long idRes,
            @RequestParam(defaultValue = "") String search
    );

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

}
