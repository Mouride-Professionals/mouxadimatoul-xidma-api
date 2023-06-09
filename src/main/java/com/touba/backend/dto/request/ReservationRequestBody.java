package com.touba.backend.dto.request;

import com.touba.backend.dto.ChambreDto;
import com.touba.backend.model.Evenement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReservationRequestBody {

    private PeriodRequest period;

    private Evenement evenement;

    private List<InviteRequest> invites;

}
