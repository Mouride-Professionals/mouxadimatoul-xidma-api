package com.touba.backend.service;

import com.touba.backend.dto.InviteDto;

public interface InviteService {

    InviteDto findByTelephone(String telephone);

}
