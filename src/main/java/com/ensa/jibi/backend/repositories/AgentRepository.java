package com.ensa.jibi.backend.repositories;

import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    boolean existsByNumTel(String numTel);

    Agent findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);

    Agent findAgentById(Long agentId);
}
