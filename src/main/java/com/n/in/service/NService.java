package com.n.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NService {

    @Autowired
    private NOrchestrator orchestrator;


    public void createWithGroq() {
        orchestrator.generateFrom("GROQ");
    }

    public void createWithGemini() {
        orchestrator.generateFrom("GEMINI");
    }
/*
    public void createWithOpenRouter() {
        orchestrator.generateFrom("OPENROUTER");
    }*/
}
