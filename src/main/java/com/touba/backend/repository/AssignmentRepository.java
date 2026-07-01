package com.touba.backend.repository;

import com.touba.backend.model.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Page<Assignment> findAllByResidenceId(Long residenceId, Pageable pageable);

    List<Assignment> findAllByAgentId(Long agentId);

    List<Assignment> findAllByAgentIdIn(List<Long> agentIds);

    Optional<Assignment> findByAgentIdAndResidenceId(Long agentId, Long residenceId);

    @Query("SELECT a FROM Assignment a WHERE a.residence.id = :residenceId AND " +
            "(lower(a.agent.prenom) LIKE %:search% OR lower(a.agent.nom) LIKE %:search% OR a.agent.telephone LIKE %:search%)")
    Page<Assignment> findAllByResidenceIdAndSearch(Pageable pageable,
                                                    @Param("residenceId") Long residenceId,
                                                    @Param("search") String search);
}
