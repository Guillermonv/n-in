package com.n.in.strategy;

import com.n.in.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IAClientFactory {

    @Autowired
    private GroqStrategy groqStrategy;

    @Autowired
    private GeminiStrategy geminiStrategy;

    public IAClientStrategy getStrategy(Agent agent) {

        return switch (agent.getProvider().toUpperCase()) {
            case "GEMINI" -> geminiStrategy;
            case "GROQ" -> groqStrategy;
            default -> throw new IllegalArgumentException("Unknown provider " + agent.getProvider());
        };
    }
}