package com.n.in.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IAClientFactory {

    @Autowired
    private GroqStrategy groqStrategy;

    @Autowired
    private  GeminiStrategy geminiStrategy;


    public IAClientStrategy get(String provider) {
        return switch (provider.toUpperCase()) {
            case "GROQ" -> groqStrategy;
            case "GEMINI" -> geminiStrategy;
        //    case "OPENROUTER" -> openRouterStrategy;
            default -> throw new IllegalArgumentException("Unknown provider: " + provider);
        };
    }
}
