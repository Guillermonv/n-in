package com.n.in.service;

import com.n.in.model.repository.NBackOfficeRepository;
import com.n.in.model.NEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBackOfficeService {

    @Autowired
    NBackOfficeRepository repository;

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
