package com.n.in.service;

import com.n.in.repository.NEntity;
import com.n.in.repository.NRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NService {
    @Autowired
    NRepository repository;
    public NEntity save(NEntity n) {
        return repository.save(n);
    }

    public Optional<NEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<NEntity> findAll() {
        return repository.findAll();
    }

    public List<NEntity> findByStatusAndImageUrlIsNull(String status) {
        return repository.findByStatusAndImageUrlIsNull(status);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
