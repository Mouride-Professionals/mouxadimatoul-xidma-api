package com.touba.backend.controller.api;

import com.touba.backend.dto.InviteDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/invites")
public interface InviteApi {

    @PostMapping
    InviteDto save(@RequestBody InviteDto dto);

}
