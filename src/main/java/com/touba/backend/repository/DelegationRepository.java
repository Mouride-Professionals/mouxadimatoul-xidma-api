package com.touba.backend.repository;

import com.touba.backend.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<Delegation, Long> {
}
