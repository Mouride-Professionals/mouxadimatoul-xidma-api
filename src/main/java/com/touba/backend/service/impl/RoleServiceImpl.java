package com.touba.backend.service.impl;

import com.touba.backend.dto.RoleDto;
import com.touba.backend.repository.RoleRepository;
import com.touba.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(RoleDto::fromEntity).collect(Collectors.toList());
    }
}
