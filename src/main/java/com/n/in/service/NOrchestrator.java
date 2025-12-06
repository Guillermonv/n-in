package com.n.in.service;

import com.n.in.model.mapper.NMapper;
import com.n.in.model.NDto;
import com.n.in.model.NEntity;
import com.n.in.model.repository.NRepository;
import com.n.in.strategy.IAClientFactory;
import com.n.in.strategy.IAClientStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NOrchestrator {

    @Autowired
    private IAClientFactory factory;
    @Autowired
    private  NRepository repository;
    @Autowired
    private  NMapper mapper;

    public NEntity generateFrom(String provider) {
        IAClientStrategy strategy = factory.get(provider);

        NDto dto = strategy.generate();

        return repository.save(mapper.toEntity(dto));
    }
}
