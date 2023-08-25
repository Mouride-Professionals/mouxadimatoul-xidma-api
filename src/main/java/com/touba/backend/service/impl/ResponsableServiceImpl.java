package com.touba.backend.service.impl;

import com.touba.backend.dto.ResponsableDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.model.Responsable;
import com.touba.backend.repository.ResponsableRepository;
import com.touba.backend.service.ResponsableService;
import com.touba.backend.validator.ResponsableValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsableServiceImpl implements ResponsableService {

    private final ResponsableRepository responsableRepository;

    @Override
    public ResponsableDto save(ResponsableDto dto) {
        List<String> errors = ResponsableValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("Responsable invalid", errors);
        }
        return ResponsableDto.fromEntity(responsableRepository.save(ResponsableDto.toEntity(dto)));
    }

    @Override
    public ResponsableDto findById(Long id) {
        return ResponsableDto.fromEntity(
                responsableRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Pas de responsable pour cet ID"))
        );
    }

    @Override
    public Page<ResponsableDto> findAllBySearch(int page, int size, String search, Long residence) {
        return responsableRepository.findAllByParams(PageRequest.of(page, size), search, residence)
                .map(ResponsableDto::fromEntity);
    }

    @Override
    public void delete(Long id) {
        Responsable responsable = responsableRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Pas de responsable pour cet ID"));
        responsableRepository.delete(responsable);
    }
}
