package com.touba.backend.controller.api;

import com.touba.backend.dto.PavillonDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/pavillons")
public interface PavillonApi {

    @PostMapping
    PavillonDto save(@RequestBody PavillonDto dto);

    @GetMapping
    List<PavillonDto> findAll();

    @GetMapping("/residence/{id}")
    List<PavillonDto> findAllByResidence(@PathVariable Long id);

    @GetMapping("/{id}")
    PavillonDto findById(@PathVariable Long id);

}
