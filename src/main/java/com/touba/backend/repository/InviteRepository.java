package com.touba.backend.repository;

import com.touba.backend.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    @Query("SELECT i FROM Invite i WHERE i.telephone LIKE %:telephone%")
    Optional<Invite> findByTelephone(String telephone);

}
