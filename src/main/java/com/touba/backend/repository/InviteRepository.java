package com.touba.backend.repository;

import com.touba.backend.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    Optional<Invite> findByTelephone(String telephone);

}
