package com.touba.backend.controller;

import com.touba.backend.controller.api.RoleApi;
import com.touba.backend.dto.RoleDto;
import com.touba.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

    @Autowired
    private RoleService roleService;

    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }
}
