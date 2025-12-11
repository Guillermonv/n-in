package com.n.in.strategy;


import com.n.in.model.Step;
import com.n.in.model.repository.AgentRepository;
import com.n.in.provider.gemini.client.GeminiClient;
import com.n.in.provider.gemini.request.Content;
import com.n.in.provider.gemini.request.GeminiRequest;
import com.n.in.provider.gemini.request.Part;
import com.n.in.provider.gemini.response.GeminiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeminiStrategy implements IAClientStrategy {

    @Autowired
    private GeminiClient geminiClient;

    @Autowired
    private GroqStrategy groqStrategy;

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Object generate(Step step) {
        try {
            return callGemini(step);
        } catch (Exception e) {
            System.out.println( e.getMessage());
            System.out.println("Gemini falló → usando fallback Groq");
            e.getMessage();
            return groqStrategy.generate(step);
        }
    }

    private Object callGemini(Step step) {

        GeminiRequest req = new GeminiRequest();
        Content c = new Content();
        Part p = new Part();
        p.setText(step.getPrompt());
        c.setParts(List.of(p));
        req.setContents(List.of(c));
        GeminiResponse res = geminiClient.sendPrompt(req,step.getAgent().getSecret());

        return res;
    }
}