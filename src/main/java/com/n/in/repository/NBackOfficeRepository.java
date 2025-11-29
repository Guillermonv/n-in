package com.n.in.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NBackOfficeRepository extends JpaRepository<NEntity, Long> {

    List<NEntity> findByStatusInOrderByCreatedDesc(List<String> statuses);
}

