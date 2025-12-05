package com.n.in.model.repository;


import com.n.in.model.AgentEntity;
import com.n.in.model.NEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {
}

