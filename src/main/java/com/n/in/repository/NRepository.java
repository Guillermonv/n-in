package com.n.in.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NRepository extends JpaRepository<NEntity, Long> {
    // Devuelve todos con status = 'initiated' y imageUrl = NULL
    List<NEntity> findByStatusAndImageUrlIsNull(String status);
}

