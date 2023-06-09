package com.touba.backend.controller.api;

import com.touba.backend.dto.ChambreDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/chambres")
public interface ChambreApi {

    @PostMapping
    ChambreDto save(@RequestBody ChambreDto dto);

    @PutMapping
    ChambreDto update(@RequestBody ChambreDto dto);
    @GetMapping("/{id}")
    ChambreDto findById(@PathVariable Long id);

    @GetMapping("/pavillon/{pavillon}")
    Page<ChambreDto> findAllByPavillon(
            @PathVariable Long pavillon,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    @GetMapping("/residence/{residence}/disponible/{debut}/{fin}")
    List<ChambreDto> findAllByPeriodAndResidence(
            @PathVariable Long residence,
            @PathVariable Date debut,
            @PathVariable Date fin
    );

}
