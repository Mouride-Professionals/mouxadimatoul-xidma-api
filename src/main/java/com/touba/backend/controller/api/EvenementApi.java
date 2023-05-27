package com.touba.backend.controller.api;

import com.touba.backend.dto.EvenementDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/evenements")
public interface EvenementApi {

    @PostMapping
    EvenementDto save(@RequestBody EvenementDto dto);

    @GetMapping
    List<EvenementDto> findAll();

    @GetMapping("/{id}")
    EvenementDto findById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

}
