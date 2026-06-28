package com.touba.backend.controller;

import com.touba.backend.controller.api.AssignmentApi;
import com.touba.backend.dto.AssignmentDto;
import com.touba.backend.dto.request.AssignmentRequest;
import com.touba.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AssignmentController implements AssignmentApi {

    private final AssignmentService assignmentService;

    @Override
    public AssignmentDto save(AssignmentRequest request) {
        return assignmentService.save(request);
    }

    @Override
    public AssignmentDto update(Long id, AssignmentRequest request) {
        return assignmentService.update(id, request);
    }

    @Override
    public AssignmentDto findById(Long id) {
        return assignmentService.findById(id);
    }

    @Override
    public Page<AssignmentDto> findAllByResidence(Long residenceId, int page, int size, String search) {
        return assignmentService.findAllByResidence(residenceId, page, size, search);
    }

    @Override
    public List<AssignmentDto> findAllByAgent(Long agentId) {
        return assignmentService.findAllByAgent(agentId);
    }

    @Override
    public void delete(Long id) {
        assignmentService.delete(id);
    }
}
