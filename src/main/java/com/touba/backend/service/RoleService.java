package com.touba.backend.service;

import com.touba.backend.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

}
