package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.ChambreDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.Chambre;
import com.touba.backend.model.Reservation;
import com.touba.backend.repository.ChambreRepository;
import com.touba.backend.repository.ReservationRepository;
import com.touba.backend.service.ChambreService;
import com.touba.backend.validator.ChambreValidator;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChambreServiceImpl implements ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ChambreDto save(ChambreDto dto) {
        List<ValidationErrorDto> errors = ChambreValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_CHAMBRE_INVALID, ErrorCode.VALIDATION_CHAMBRE_INVALID, errors);
        }
        Chambre chambre = ChambreDto.toEntity(dto);
        chambre.setReference(
                Arrays.stream(dto.getPavillon().getLibelle().trim().split("\\s+"))
                        .map(word -> word.charAt(0))
                        .map(String::valueOf)
                        .collect(Collectors.joining())
                        .toUpperCase() + "-" + dto.getNiveau().toString() + dto.getNumero()
        );
        return ChambreDto.fromEntity(chambreRepository.save(chambre));
    }

    @Override
    public ChambreDto update(ChambreDto dto) {
        List<ValidationErrorDto> errors = ChambreValidator.validateUpdate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_CHAMBRE_INVALID, ErrorCode.VALIDATION_CHAMBRE_INVALID, errors);
        }
        return ChambreDto.fromEntity(chambreRepository.save(ChambreDto.toEntity(dto)));
    }

    @Override
    public ChambreDto findById(Long id) {
        if (id == null) {
            throw new EntityInvalidException(ErrorCode.CHAMBRE_ID_REQUIRED, ErrorCode.VALIDATION_CHAMBRE_INVALID);
        }
        return ChambreDto.fromEntity(
                chambreRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.CHAMBRE_NOT_FOUND, ErrorCode.CHAMBRE_NOT_FOUND)
                )
        );
    }

    @Override
    public Page<ChambreDto> findAllByPavillon(Long pavillon, int page, int size) {
        if (pavillon == null) {
            throw new EntityInvalidException(ErrorCode.CHAMBRE_PAVILLON_ID_REQUIRED, ErrorCode.VALIDATION_CHAMBRE_INVALID);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("numero"));
        return chambreRepository.findAllByPavillon(pavillon, pageable).map(ChambreDto::fromEntity);
    }

    @Override
    public List<ChambreDto> findAllByPeriodAndResidence(Long residence, Date debut, Date fin) {
        List<ChambreDto> chambresReservees = reservationRepository.findAllByPeriodAndResidence(debut, fin, residence);
        List<Long> idIndisponibles = chambresReservees.stream()
                .filter(c -> c.getNombrePlace() <= c.getPlaceReservee())
                .map(
                        ChambreDto::getId
                ).toList();
        if (idIndisponibles.isEmpty()) {
            idIndisponibles = new ArrayList<>(List.of(0L));
        }
        return chambreRepository.findAllByResidenceAvailable(residence, idIndisponibles)
                .stream().map(ChambreDto::fromEntity)
                .peek(c -> {
                    List<ChambreDto> c1 = chambresReservees.stream().filter(ch -> ch.getId().equals(c.getId())).toList();
                    if (!c1.isEmpty()) {
                        c.setPlaceReservee(c1.get(0).getPlaceReservee());
                    }
                })
                .collect(Collectors.toList());
    }
}
