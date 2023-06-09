package com.touba.backend.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class PeriodRequest {
    private Date entree;
    private Date sortie;
}
