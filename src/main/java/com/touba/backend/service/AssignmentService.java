package com.touba.backend.service;

import com.touba.backend.dto.AssignmentDto;
import com.touba.backend.dto.request.AssignmentRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AssignmentService {

    AssignmentDto save(AssignmentRequest request);

    AssignmentDto update(Long id, AssignmentRequest request);

    AssignmentDto findById(Long id);

    Page<AssignmentDto> findAllByResidence(Long residenceId, int page, int size, String search);

    List<AssignmentDto> findAllByAgent(Long agentId);

    void delete(Long id);
}
