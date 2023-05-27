package com.touba.backend.controller.api;

import com.touba.backend.dto.RoleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/roles")
public interface RoleApi {

    @GetMapping
    List<RoleDto> findAll();

}
