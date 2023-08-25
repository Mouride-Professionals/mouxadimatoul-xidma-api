package com.touba.backend.service;

import com.touba.backend.dto.InviteDto;

public interface InviteService {

    InviteDto save(InviteDto dto);

    InviteDto findByTelephone(String telephone);

}
