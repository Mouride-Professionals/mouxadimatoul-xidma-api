package com.touba.backend.dto.stats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TotalStatsDto {
    private Long pavillons;
    private Long chambres;
    private Long delegations;
    private Long reservations;
}
