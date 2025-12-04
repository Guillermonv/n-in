package com.n.in.strategy;


import com.n.in.model.NDto;
import com.n.in.provider.gemini.client.GeminiClient;
import com.n.in.provider.gemini.request.Content;
import com.n.in.provider.gemini.request.GeminiRequest;
import com.n.in.provider.gemini.request.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class GeminiStrategy implements IAClientStrategy {

    @Autowired
    private GeminiClient geminiClient;

    @Autowired
    private GroqStrategy groqStrategy;

    @Override
    public NDto generate() {
        try {
            return callGemini();
        } catch (Exception e) {
            System.out.println("Gemini falló → usando fallback Groq");
            return groqStrategy.generate();
        }
    }

    private NDto callGemini() {
        GeminiRequest req = new GeminiRequest();
        Content c = new Content();
        Part p = new Part();
        p.setText("dame algo lindo");
        c.setParts(List.of(p));
        req.setContents(List.of(c));

        var res = geminiClient.sendPrompt(req);

        return NDto.builder()
                .type("IA")
                .subType("GEMINI")
                .status("initiated")
                .description(res.getCandidates().get(0).getContent().getParts().get(0).getText())
                .lastUpdated(LocalDateTime.now())
                .created(LocalDateTime.now())
                .build();
    }
}