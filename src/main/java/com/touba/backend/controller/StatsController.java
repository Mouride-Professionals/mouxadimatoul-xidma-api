package com.touba.backend.controller;

import com.touba.backend.controller.api.StatsApi;
import com.touba.backend.dto.stats.ChambreDispoDto;
import com.touba.backend.dto.stats.TotalStatsDto;
import com.touba.backend.service.impl.StatsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController implements StatsApi {

    private final StatsServiceImpl statsService;

    @Override
    public TotalStatsDto getStatsByResidence(Long residence) {
        return statsService.getStatsByResidence(residence);
    }

    @Override
    public List<ChambreDispoDto> getChambreDispoByResidence(Long residence) {
        return statsService.getChambreDispoByResidence(residence);
    }
}
