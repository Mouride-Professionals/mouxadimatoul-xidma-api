package com.touba.backend.service;

import com.touba.backend.dto.ChambreDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface ChambreService {

    ChambreDto save(ChambreDto dto);

    ChambreDto update(ChambreDto dto);

    ChambreDto findById(Long id);

    Page<ChambreDto> findAllByPavillon(Long pavillon, int page, int size);

    List<ChambreDto> findAllByPeriodAndResidence(Long residence, Date debut, Date fin);

}
