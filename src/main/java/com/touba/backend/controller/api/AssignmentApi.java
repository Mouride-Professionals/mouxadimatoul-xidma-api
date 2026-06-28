package com.touba.backend.controller.api;

import com.touba.backend.dto.AssignmentDto;
import com.touba.backend.dto.request.AssignmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/assignments")
public interface AssignmentApi {

    @PostMapping
    AssignmentDto save(@RequestBody AssignmentRequest request);

    @PutMapping("/{id}")
    AssignmentDto update(@PathVariable Long id, @RequestBody AssignmentRequest request);

    @GetMapping("/{id}")
    AssignmentDto findById(@PathVariable Long id);

    @GetMapping
    Page<AssignmentDto> findAllByResidence(
            @RequestParam Long residenceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String search
    );

    @GetMapping("/agent/{agentId}")
    List<AssignmentDto> findAllByAgent(@PathVariable Long agentId);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
