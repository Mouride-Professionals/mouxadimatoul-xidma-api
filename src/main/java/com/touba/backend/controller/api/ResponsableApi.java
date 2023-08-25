package com.touba.backend.controller.api;

import com.touba.backend.dto.ResponsableDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/responsables")
public interface ResponsableApi {

    @PostMapping()
    ResponsableDto save(@RequestBody ResponsableDto dto);

    @GetMapping("/{id}")
    ResponsableDto findById(@PathVariable Long id);

    @GetMapping
    Page<ResponsableDto> findAllBySearch(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "-1") Long residence
    );

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

}
