package com.touba.backend.controller;

import com.touba.backend.controller.api.InviteApi;
import com.touba.backend.dto.InviteDto;
import com.touba.backend.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InviteController implements InviteApi {

    private final InviteService inviteService;

    @Override
    public InviteDto save(InviteDto dto) {
        return inviteService.save(dto);
    }
}
