package com.touba.backend.service.impl;

import com.touba.backend.repository.InviteRepository;
import com.touba.backend.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRepository inviteRepository;

}
