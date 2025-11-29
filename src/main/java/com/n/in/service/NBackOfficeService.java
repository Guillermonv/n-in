package com.n.in.service;

import com.n.in.repository.NBackOfficeRepository;
import com.n.in.repository.NEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NBackOfficeService {

    @Autowired
    NBackOfficeRepository repository;


    public NEntity save(NEntity n) {
        return repository.save(n);
    }

    public Optional<NEntity> findById(Long id) {
        return repository.findById(id);
    }


    public List<NEntity> findByStatuses(List<String> statuses) {
        return repository.findByStatusInOrderByCreatedDesc(statuses);
    }

    public boolean updateStatus(Long id, String status) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setStatus(status);
                    repository.save(entity);
                    return true;
                })
                .orElse(false);
    }

}
