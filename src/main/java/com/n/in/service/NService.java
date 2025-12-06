package com.n.in.service;

import com.n.in.utils.enums.ProviderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NService {

    @Autowired
    private NOrchestrator orchestrator;


    public void createWithGroq() {
        orchestrator.generateFrom(ProviderEnum.GROQ.getName());
    }

    public void createWithGemini() {
        orchestrator.generateFrom(ProviderEnum.GEMINI.getName());
    }
/*
    public void createWithOpenRouter() {
        orchestrator.generateFrom("OPENROUTER");
    }*/
}
