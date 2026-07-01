package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.*;
import com.touba.backend.dto.request.ReservationRequestBody;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.Reservation;
import com.touba.backend.repository.InviteRepository;
import com.touba.backend.repository.ReservationRepository;
import com.touba.backend.service.ReservationService;
import com.touba.backend.utils.ExportPdfFile;
import com.touba.backend.utils.UploadExcelFile;
import com.touba.backend.validator.ReservationValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    @Transactional
    public List<ReservationDto> save(ReservationRequestBody request) {
        List<ValidationErrorDto> errors = ReservationValidator.validateBody(request);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_RESERVATION_INVALID, ErrorCode.VALIDATION_RESERVATION_INVALID, errors);
        }
        List<Reservation> reservations = new ArrayList<>();
        request.getInvites().forEach(inv -> {
            Reservation reservation = new Reservation();
            reservation.setDateEntree(request.getPeriod().getEntree());
            reservation.setDateSortie(request.getPeriod().getSortie());
            reservation.setDateSortieProvisoire(request.getPeriod().getSortie());
            reservation.setEvenement(request.getEvenement());
            reservation.setAccueillant(AccueillantDto.toEntity(inv.getAccueillant()));
            reservation.setResponsable(ResponsableDto.toEntity(inv.getResponsable()));
            reservation.setPresence(inv.getPresence());
            reservation.setChambre(ChambreDto.toEntity(inv.getChambre()));
            reservation.setInvite(
                    inviteRepository.findByTelephone(inv.getTelephone()).orElse(
                            null
                    )
            );
            reservations.add(reservation);
        });
        return reservationRepository.saveAll(reservations).stream().map(ReservationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ReservationDto update(ReservationDto dto) {
        Reservation reservation = reservationRepository.findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.RESERVATION_NOT_FOUND, ErrorCode.RESERVATION_NOT_FOUND)
        );
        reservation.setDateEntree(dto.getDateEntree());
        reservation.setDateSortie(dto.getDateSortie());
        reservation.setDateSortieProvisoire(dto.getDateSortie());
        reservation.setAccueillant(AccueillantDto.toEntity(dto.getAccueillant()));
        reservation.setResponsable(ResponsableDto.toEntity(dto.getResponsable()));
        reservation.setChambre(ChambreDto.toEntity(dto.getChambre()));
        reservation.setPresence(dto.getPresence());
        return ReservationDto.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto findById(Long id) {
        return reservationRepository.findById(id)
                .map(ReservationDto::fromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.RESERVATION_NOT_FOUND, ErrorCode.RESERVATION_NOT_FOUND)
                );
    }

    @Override
    public List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon) {
        return reservationRepository.findAllByPeriodeAndPavillon(debut, fin, pavillon).stream().map(ReservationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<ReservationDto> findAll(int page, int size, int year, Long event, Long residence, int presence) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by("dateEntree").ascending());
        return reservationRepository.findAll(pageable, year, event, residence, presence).map(ReservationDto::fromEntity);
    }

    @Override
    public void exportExcelFile(HttpServletResponse response, Long residence, int year, Long event, int presence, String locale) throws IOException {
        List<Reservation> reservations = reservationRepository.findAll(year, event, residence, presence);
        response.setContentType("application/octet-stream");
        if (!reservations.isEmpty()) {
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=reservation " + reservations.get(0).getEvenement().getLibelle() + " " + year + ".xlsx";
            response.setHeader(headerKey, headerValue);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            UploadExcelFile excelFile = new UploadExcelFile(
                    reservations.stream().map(reservation -> FileReservationDto.mapToFile(reservation, locale)).collect(Collectors.toList()),
                    locale
            );
            excelFile.generateExcelFile(response);
        }
    }

    @Override
    public void exportPdfFile(HttpServletResponse response, Long residence, int year, Long event, int presence, String locale) throws IOException {
        List<Reservation> reservations = reservationRepository.findAll(year, event, residence, presence);
        if (!reservations.isEmpty()) {
            String filename = "reservation " + reservations.get(0).getEvenement().getLibelle() + " " + year + ".pdf";
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            ExportPdfFile pdfFile = new ExportPdfFile(
                    reservations.stream().map(r -> FileReservationDto.mapToFile(r, locale)).collect(Collectors.toList()),
                    locale
            );
            pdfFile.generatePdfFile(response);
        }
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.RESERVATION_NOT_FOUND, ErrorCode.RESERVATION_NOT_FOUND));
        reservationRepository.delete(reservation);
    }
}
