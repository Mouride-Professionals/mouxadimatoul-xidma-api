package com.touba.backend.controller.api;

import com.touba.backend.dto.stats.ChambreDispoDto;
import com.touba.backend.dto.stats.TotalStatsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/stats")
public interface StatsApi {

    @GetMapping("/{residence}")
    TotalStatsDto getStatsByResidence(@PathVariable Long residence);

    @GetMapping("/{residence}/chambres")
    List<ChambreDispoDto> getChambreDispoByResidence(@PathVariable Long residence);

}
