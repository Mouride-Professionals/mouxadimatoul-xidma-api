package com.touba.backend.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DelegationRequest {
    private Long id;
    private String nom;
    private Integer nombre;
}
