package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.InviteDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.repository.InviteRepository;
import com.touba.backend.service.InviteService;
import com.touba.backend.validator.InviteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    public InviteDto save(InviteDto dto) {
        List<ValidationErrorDto> errors = InviteValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_INVITE_INVALID, ErrorCode.VALIDATION_INVITE_INVALID, errors);
        }
        return InviteDto.fromEntity(inviteRepository.save(InviteDto.toEntity(dto)));
    }

    @Override
    public InviteDto findByTelephone(String telephone) {
        return InviteDto.fromEntity(
                inviteRepository.findByTelephone(telephone).orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.INVITE_NOT_FOUND, ErrorCode.INVITE_NOT_FOUND)
                )
        );
    }
}
